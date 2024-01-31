package com.azalea.cathayassignment.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class CoinDeskResponse {
    @Getter
    @Setter
    private TimeInfo time;
    private String disclaimer;

    @Getter
    @Setter
    private String chartName;

    @Getter
    @Setter
    private Map<String, CurrencyInfo> bpi;

}
