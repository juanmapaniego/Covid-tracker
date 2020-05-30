package com.jmpaniego.coronavirustracker.controllers;

import com.jmpaniego.coronavirustracker.models.CovidArgentina;
import com.jmpaniego.coronavirustracker.models.LocationStats;
import com.jmpaniego.coronavirustracker.services.CoronaVirusDataService;
import com.jmpaniego.coronavirustracker.services.CoronaVirusDataServiceArg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalDeads",covidArgentinas.stream().filter(c -> c.equals("SI")).count());
        return "arg";
    }
}
