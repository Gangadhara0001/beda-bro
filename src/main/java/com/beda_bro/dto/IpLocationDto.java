package com.beda_bro.dto;

import lombok.Data;

@Data
public class IpLocationDto {

    private String ip;

    private String city;

    private String region;

    private String country_name;

    private Double latitude;

    private Double longitude;

}