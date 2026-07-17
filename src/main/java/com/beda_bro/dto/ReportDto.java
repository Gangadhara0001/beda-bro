package com.beda_bro.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
public class ReportDto {
    private Long id;

    private String activity;

    private String landmark;

    private String frequency;

    private String observedDate;

    private String observedTime;

    private String description;

    private String vehicleNumber;

    private String vehicleType;

    private String vehicleColour;

    private String urgency;

    private MultipartFile[] evidenceFiles;

    private String suspectName;

    private Integer suspectAge;

    private String gender;

    private Integer numberOfPersons;

    private String remarks;

    private Boolean agree;

    private String qrCode;

    private String policeStation;

    private String location;

    private String evidenceFileName;

}