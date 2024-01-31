package com.azalea.cathayassignment.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class CurrencyInfo {
    @Getter
    @Setter
    private String code;

    @Setter
    @JsonProperty("zh_code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String zhCode;

    private String symbol;
    private String rate;
    private String description;
    @JsonProperty("rate_float")
    @Setter
    private float rateFloat;


}
