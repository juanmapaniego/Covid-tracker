package com.jmpaniego.coronavirustracker.services;


import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.jmpaniego.coronavirustracker.models.CovidArgentina;
import com.jmpaniego.coronavirustracker.models.LocationStats;
import com.jmpaniego.coronavirustracker.models.Sexo;
import com.jmpaniego.coronavirustracker.utils.FormattedDateMatcher;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataServiceArg {

    private static String VIRUS_DATA_URL = "http://170.150.153.128/covid/covid_19_casos.csv";
    private List<CovidArgentina> allStats = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException, ParseException {
        List<CovidArgentina> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvReader = new StringReader(response.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(';')
                                        .withFirstRecordAsHeader().
                                        parse(csvReader);
        SimpleDateFormat sdfIso8601 = new SimpleDateFormat("yyyy-MM-dd");
        String fecha;
        for (CSVRecord record : records){
            CovidArgentina covid = new CovidArgentina();
            covid.setId(Integer.parseInt(record.get("\uFEFFid_evento_caso")));
            covid.setSexo(Sexo.valueOf(record.get("sexo")));
            covid.setEdad(record.get("edad_actual_anios"));
            covid.setPais_residencia(record.get("pais_residencia"));
            covid.setProvincia_residencia(record.get("provincia_residencia"));
            covid.setProv_residencia_id(Integer.parseInt(record.get("prov_residencia_id")));
            covid.setProvincia_carga(record.get("provincia_carga"));
            covid.setProv_carga_id(Integer.parseInt(record.get("prov_carga_id")));
            covid.setFis(record.get("fis"));
            covid.setAntecedente_epidemiologico(record.get("antecedente_epidemiologico"));
            fecha = record.get("fecha_apertura");
            if(FormattedDateMatcher.matches(fecha)) {
                covid.setFecha_apertura(sdfIso8601.parse(fecha));
            }
            covid.setSepi_apertura(Integer.parseInt(record.get("sepi_apertura")));
            fecha = record.get("fecha_internacion");
            if(FormattedDateMatcher.matches(fecha)){
                covid.setFecha_internacion(sdfIso8601.parse(fecha));
            }
            covid.setCuidado_intensivo(record.get("cuidado_intensivo"));
            fecha = record.get("fecha_cui_intensivo");
            if (FormattedDateMatcher.matches(fecha))
                covid.setFecha_cui_intensivo(sdfIso8601.parse(fecha));
            covid.setFallecido(record.get("fallecido"));
            fecha = record.get("fecha_fallecimiento");
            if(FormattedDateMatcher.matches(fecha)){
                covid.setFecha_fallecimiento(sdfIso8601.parse(fecha));
            }
            covid.setAsist_resp_mecanica(record.get("asist_resp_mecanica"));
            covid.setOrigen_financiamiento(record.get("origen_financiamiento"));
            covid.setClasificacion(record.get("clasificacion"));
            covid.setClasificacion_resumen(record.get("clasificacion_resumen"));
            fecha = record.get("fecha_diagnostico");
            if(FormattedDateMatcher.matches(fecha)){
                covid.setFecha_diagnostico(sdfIso8601.parse(fecha));
            }
            newStats.stream().map(r -> r.getFallecido()).forEach(System.out::println);
            newStats.add(covid);
        }
        this.allStats = newStats;
    }

    public List<CovidArgentina> getAllStats() {
        return allStats;
    }
}
