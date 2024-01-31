package com.azalea.cathayassignment.domain.dto;

import lombok.Getter;

import java.util.Map;

public class CoinDeskResponse {
    @Getter
    private TimeInfo time;
    private String disclaimer;

    @Getter
    private String chartName;

    @Getter
    private Map<String, CurrencyInfo> bpi;
}
