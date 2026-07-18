package com.beda_bro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QrLocationDto {

    private String qrCode;
    private String policeStation;
    private String location;
    private double longitude;
    private double latitude;

}