package com.jmpaniego.coronavirustracker.controllers;

import com.jmpaniego.coronavirustracker.models.CovidArgentina;
import com.jmpaniego.coronavirustracker.models.CovidProvincia;
import com.jmpaniego.coronavirustracker.models.LocationStats;
import com.jmpaniego.coronavirustracker.services.CoronaVirusDataService;
import com.jmpaniego.coronavirustracker.services.CoronaVirusDataServiceArg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class JsonController {

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    @Autowired
    private CoronaVirusDataServiceArg coronaVirusDataServiceArg;

    @GetMapping("/world")
    public List<LocationStats> world(  @RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize){
        PagedListHolder<LocationStats> paged = new PagedListHolder<>(coronaVirusDataService.getAllStats());
        paged.setPageSize(pageSize);
        paged.setPage(pageNo);

        return paged.getPageList();

    }

    @GetMapping("/arg")
    public List<CovidArgentina> arg(@RequestParam(defaultValue = "0") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize){
        PagedListHolder<CovidArgentina> paged = new PagedListHolder<>(coronaVirusDataServiceArg.getAllStats());
        paged.setPageSize(pageSize);
        paged.setPage(pageNo);

        return paged.getPageList();

    }

    @GetMapping("/arg/byProv")
    public List<CovidProvincia> porProvincia(@RequestParam(defaultValue = "0") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize){
        List<CovidArgentina> covidArgentinas = coronaVirusDataServiceArg.getAllStats();
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
        PagedListHolder<CovidProvincia> paged = new PagedListHolder<>(listaPorProv);
        paged.setPageSize(pageSize);
        paged.setPage(pageNo);

        return paged.getPageList();

    }

    @GetMapping(value = "/arg/byProv/{nombre_provincia}")
    public ResponseEntity<CovidProvincia> byNombreProvincia(@PathVariable("nombre_provincia") String provincia){
        List<CovidArgentina> covidArgentinas = coronaVirusDataServiceArg.getAllStats();
        Map<String, List<CovidArgentina>> porProv= covidArgentinas.
                stream().
                collect(Collectors.groupingBy(CovidArgentina::getProvincia_carga));
        Optional<CovidProvincia> res = porProv.entrySet().stream().map(e -> {
            CovidProvincia prov = new CovidProvincia();
            prov.setNombre(e.getKey());
            prov.setSospechosos(e.getValue().size());
            prov.setConfirmados((int) e.getValue().stream().filter(r->r.getClasificacion_resumen().equals("Confirmado")).count());
            prov.setFallecidos((int) e.getValue().stream().filter(r -> r.getFallecido().equals("SI")).count());
            return prov;
        }).filter(p -> p.getNombre().equals(provincia)).findFirst();
        if(res.isPresent())
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
