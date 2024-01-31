package com.azalea.cathayassignment.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class CurrencyInfo {
    @Getter
    private String code;

    @Setter
    @JsonProperty("zh_code")
    private String zhCode;

    private String symbol;
    private String rate;
    private String description;
    @JsonProperty("rate_float")
    private float rateFloat;


}
