package com.azalea.cathayassignment.domain.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class TimeInfo {
    @Getter
    @Setter
    private String updated;

    @JsonIgnore
    private String updatedISO;

    @JsonIgnore
    private String updateduk;

}

