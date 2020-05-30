package com.jmpaniego.coronavirustracker.controllers;

import com.jmpaniego.coronavirustracker.models.CovidArgentina;
import com.jmpaniego.coronavirustracker.models.CovidProvincia;
import com.jmpaniego.coronavirustracker.models.LocationStats;
import com.jmpaniego.coronavirustracker.services.CoronaVirusDataService;
import com.jmpaniego.coronavirustracker.services.CoronaVirusDataServiceArg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @Autowired
    CoronaVirusDataServiceArg coronaVirusDataServiceArg;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewReportedCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewReportedCases",totalNewReportedCases);
        return "home";
    }

    @GetMapping("/arg")
    public String arg(Model model){
        List<CovidArgentina> covidArgentinas = coronaVirusDataServiceArg.getAllStats();
        int totalReportedCases = covidArgentinas.size();
        model.addAttribute("totalCases",totalReportedCases);
        model.addAttribute("totalConfirmedCases",covidArgentinas.stream().filter(c -> c.getClasificacion_resumen().equals("Confirmado")).count());
        model.addAttribute("totalDeads",covidArgentinas.stream().filter(c -> c.getFallecido().equals("SI")).count());

        Map<String, List<CovidArgentina>> porProv= covidArgentinas.
                stream().
                collect(Collectors.groupingBy(CovidArgentina::getProvincia_carga));
        List<CovidProvincia> listaPorProv = porProv.entrySet().stream().map(e -> {
            CovidProvincia prov = new CovidProvincia();
            prov.setNombre(e.getKey());
            prov.setSospechosos(e.getValue().size());
            prov.setConfirmados((int) e.getValue().stream().filter(r->r.getClasificacion_resumen().equals("Confirmado")).count());
            prov.setFallecidos((int) e.getValue().stream().filter(r -> r.getFallecido().equals("SI")).count());
            return prov;
        }).collect(Collectors.toList());
        model.addAttribute("listaPorProv",listaPorProv);
        return "arg";
    }
}
