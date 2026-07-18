package com.beda_bro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrLocationDto {

    private String qrCode;
    private String policeStation;
    private String location;
    private double longitude;
    private double latitude;


}