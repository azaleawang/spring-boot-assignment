package com.azalea.cathayassignment.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

public class TimeInfo {
    @Getter
    @Setter
    private String updated;

    @JsonIgnore
    private String updatedISO;

    @JsonIgnore
    private String updateduk;

}

