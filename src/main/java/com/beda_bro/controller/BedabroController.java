package com.beda_bro.controller;

import com.beda_bro.dto.QrLocationDto;
import com.beda_bro.dto.ReportDto;
import com.beda_bro.storage.ReportStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;


@Controller
public class BedabroController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/report")
    public String report(
            @RequestParam String qr,
            Model model) {

        QrLocationDto qrLocation;

        switch (qr) {

            case "QR001":
                qrLocation = new QrLocationDto(
                        "QR001",
                        "Electronic City Police Station",
                        "Electronic City Bus Stop",
                        12.8399,
                        77.6770
                );
                break;

            case "QR002":
                qrLocation = new QrLocationDto(
                        "QR002",
                        "Parappana Agrahara Police Station",
                        "Silk Institute Metro Station",
                        12.8856,
                        77.6589
                );
                break;

            case "QR003":
                qrLocation = new QrLocationDto(
                        "QR003",
                        "Bandepalya Police Station",
                        "Bandepalya Bus Stop",
                        12.8922,
                        77.6205
                );
                break;

            case "QR004":
                qrLocation = new QrLocationDto(
                        "QR004",
                        "Begur Police Station",
                        "Begur Bus Stop",
                        12.8888,
                        77.6373
                );
                break;

            case "QR005":
                qrLocation = new QrLocationDto(
                        "QR005",
                        "Hulimavu Police Station",
                        "Hulimavu Metro Station",
                        12.8768,
                        77.6048
                );
                break;

            case "QR006":
                qrLocation = new QrLocationDto(
                        "QR006",
                        "Hebbagodi Police Station",
                        "Hebbagodi Bus Stop",
                        12.8238,
                        77.6893
                );
                break;

            default:
                qrLocation = new QrLocationDto(
                        qr,
                        "Unknown Station",
                        "Unknown Location",
                        0.0,
                        0.0
                );
        }

        model.addAttribute("qrLocation", qrLocation);

        return "index";
    }

    @PostMapping("/submitReport")
    public String home(@ModelAttribute ReportDto report,Model model) throws Exception {

        report.setId(System.currentTimeMillis());

        if(report.getEvidenceFiles()!=null
                && report.getEvidenceFiles().length>0){

            MultipartFile file = report.getEvidenceFiles()[0];

            if(!file.isEmpty()){

                String fileName =
                        System.currentTimeMillis()+"_"
                                +file.getOriginalFilename();

                /*Path path =
                        Paths.get("uploads",fileName);

                Files.copy(file.getInputStream(),
                        path);*/


                Path uploadDir = Paths.get("uploads");

                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                Path path = uploadDir.resolve(fileName);

                Files.copy(file.getInputStream(), path);

                report.setEvidenceFileName(fileName);

                report.setEvidenceFileName(fileName);

            }

        }



        ReportStorage.reports.add(report);

        System.out.println(report);

        model.addAttribute("qrCode", report.getQrCode());

        return "success";
    }
}

