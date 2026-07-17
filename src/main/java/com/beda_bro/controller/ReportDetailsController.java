package com.beda_bro.controller;

import com.beda_bro.dto.ReportDto;
import com.beda_bro.storage.ReportStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReportDetailsController {

    @GetMapping("/report-details")
    public String details(@RequestParam Long id,
                          Model model){

        ReportDto report = ReportStorage.reports.stream()

                .filter(r -> r.getId().equals(id))

                .findFirst()

                .orElse(null);

        model.addAttribute("report", report);

        return "report-details";
    }

}