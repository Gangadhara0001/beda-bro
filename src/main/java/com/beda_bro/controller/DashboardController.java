package com.beda_bro.controller;

import com.beda_bro.dto.ReportDto;
import com.beda_bro.storage.ReportStorage;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
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

        model.addAttribute("count",ReportStorage.reports.size());

        return "dashboard";
    }

    /*@PostMapping("/assign")
    @ResponseBody
    public String assignReport(
            @RequestParam Long id,
            @RequestParam String assignee){

        ReportStorage.reports.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .ifPresent(r -> r.setAssignee(assignee));

        return "dashboard";
    }*/

    @PostMapping("/assign-case")
    public String assignCase(@RequestParam Long id,
                             @RequestParam String assignee) {

        for (ReportDto report : ReportStorage.reports) {

            if (report.getId().equals(id)) {

                report.setAssignee(assignee);

                break;
            }
        }

        return "redirect:/dashboard";
    }

    /*@GetMapping("/download-report")
    public void downloadReport(
            @RequestParam Long id,
            HttpServletResponse response) throws Exception {

        ReportDto report = ReportStorage.reports.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(report == null){
            return;
        }

        response.setContentType("text/plain");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=Report_" + report.getQrCode() + ".txt");

        PrintWriter out = response.getWriter();

        out.println("BEDA BRO REPORT");
        out.println("---------------------------");
        out.println("QR Code : " + report.getQrCode());
        out.println("Police Station : " + report.getPoliceStation());
        out.println("Location : " + report.getLocation());
        out.println("Activity : " + report.getActivity());
        out.println("Priority : " + report.getUrgency());
        out.println("Assigned Officer : " +
                (report.getAssignee() == null ? "Not Assigned" : report.getAssignee()));
        out.println();
        out.println("Description");
        out.println(report.getDescription());

        out.flush();
    }*/

    @GetMapping("/download-report")
    public void downloadReport(
            @RequestParam Long id,
            HttpServletResponse response) throws Exception {

        ReportDto report = ReportStorage.reports.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(report==null){
            return;
        }

        response.setContentType("application/pdf");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=BEDA_BRO_Report_"+report.getQrCode()+".pdf");

        Document document=new Document(PageSize.A4);

        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font title=new Font(Font.FontFamily.HELVETICA,20,Font.BOLD);

        Font heading=new Font(Font.FontFamily.HELVETICA,14,Font.BOLD);

        Font normal=new Font(Font.FontFamily.HELVETICA,12);

        Paragraph p=new Paragraph("BEDA BRO\nAnonymous Drug Reporting System",title);

        p.setAlignment(Element.ALIGN_CENTER);

        document.add(p);

        document.add(new Paragraph(" "));

        document.add(new Paragraph("CASE REPORT",heading));

        document.add(new Paragraph(" "));

        document.add(new Paragraph("QR Code : " + report.getQrCode(), normal));

        document.add(new Paragraph("Police Station : " + report.getPoliceStation(), normal));

        document.add(new Paragraph("Assigned Officer : " +
                (report.getAssignee() == null || report.getAssignee().isEmpty()
                        ? "Not Assigned"
                        : report.getAssignee()), normal));

        document.add(new Paragraph("Status : " +
                (report.getAssignee() == null || report.getAssignee().isEmpty()
                        ? "NEW"
                        : "ASSIGNED"), normal));

        document.add(new Paragraph(" "));

        document.add(new Paragraph("INCIDENT DETAILS", heading));

        document.add(new Paragraph("Activity : " + report.getActivity(), normal));

        document.add(new Paragraph("Location : " + report.getLocation(), normal));

        document.add(new Paragraph("Landmark : " + report.getLandmark(), normal));

        document.add(new Paragraph("Observed Date : " + report.getObservedDate(), normal));

        document.add(new Paragraph("Observed Time : " + report.getObservedTime(), normal));

        document.add(new Paragraph("Frequency : " + report.getFrequency(), normal));

        document.add(new Paragraph("Priority : " + report.getUrgency(), normal));

        document.add(new Paragraph("Latitude : " + report.getLatitude(), normal));

        document.add(new Paragraph("Longitude : " + report.getLongitude(), normal));

        document.add(new Paragraph(" "));

        document.add(new Paragraph("SUSPECT DETAILS", heading));

        document.add(new Paragraph("Suspect Name : " +
                (report.getSuspectName() == null ? "Not Provided" : report.getSuspectName()), normal));

        document.add(new Paragraph("Mobile Number : " +
                (report.getSuspectMobileNumber() == null ? "Not Provided" : report.getSuspectMobileNumber()), normal));

        document.add(new Paragraph("Age : " +
                (report.getSuspectAge() == null ? "Not Provided" : report.getSuspectAge()), normal));

        document.add(new Paragraph("Gender : " +
                (report.getGender() == null ? "Not Provided" : report.getGender()), normal));

        document.add(new Paragraph("Number of Persons : " +
                (report.getNumberOfPersons() == null ? "Not Provided" : report.getNumberOfPersons()), normal));

        document.add(new Paragraph(" "));

        document.add(new Paragraph("VEHICLE DETAILS", heading));

        document.add(new Paragraph("Vehicle Number : " +
                (report.getVehicleNumber() == null ? "Not Provided" : report.getVehicleNumber()), normal));

        document.add(new Paragraph("Vehicle Type : " +
                (report.getVehicleType() == null ? "Not Provided" : report.getVehicleType()), normal));

        document.add(new Paragraph("Vehicle Colour : " +
                (report.getVehicleColour() == null ? "Not Provided" : report.getVehicleColour()), normal));

        document.add(new Paragraph(" "));

        document.add(new Paragraph("DESCRIPTION", heading));

        document.add(new Paragraph(
                report.getDescription() == null ? "Not Provided" : report.getDescription(),
                normal));

        document.add(new Paragraph(" "));

        document.add(new Paragraph("ADDITIONAL REMARKS", heading));

        document.add(new Paragraph(
                report.getRemarks() == null ? "Not Provided" : report.getRemarks(),
                normal));

        document.add(new Paragraph(" "));

        document.add(new Paragraph("Reporter Consent : " +
                (Boolean.TRUE.equals(report.getAgree()) ? "Agreed" : "Not Agreed"), normal));

        document.add(new Paragraph(" "));

        if(report.getEvidenceFileName()!=null){

            try{

                Image image=Image.getInstance(
                        "uploads/"+report.getEvidenceFileName());

                image.scaleToFit(350,350);

                image.setAlignment(Image.ALIGN_CENTER);

                document.add(new Paragraph("Evidence",heading));

                document.add(image);

            }catch(Exception ex){

                document.add(new Paragraph(
                        "Evidence image unavailable.",normal));

            }

        }

        document.add(new Paragraph(" "));

        Paragraph footer=new Paragraph(
                "Generated by BEDA BRO\n"
                        +"Confidential Police Record",
                heading);

        footer.setAlignment(Element.ALIGN_CENTER);

        document.add(footer);

        document.close();

    }

}