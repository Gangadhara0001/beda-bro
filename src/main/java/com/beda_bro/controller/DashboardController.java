package com.beda_bro.controller;

import com.beda_bro.dto.ReportDto;
import com.beda_bro.storage.ReportStorage;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session,
                            Model model){

        /*String station=(String)session.getAttribute("station");

        if(station==null){

            return "redirect:/login";
        }*/

        /*List<ReportDto> reports =
                ReportStorage.reports.stream()
                        .filter(r->station.equals(r.getPoliceStation()))
                        .collect(Collectors.toList());*/

        //model.addAttribute("station",station);

        model.addAttribute("reports",ReportStorage.reports);

        //model.addAttribute("count",reports.size());

        return "dashboard";
    }

}