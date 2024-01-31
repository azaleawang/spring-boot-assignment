package com.azalea.cathayassignment.controllers;

import com.azalea.cathayassignment.domain.dto.CoinDeskResponse;
import com.azalea.cathayassignment.domain.dto.CurrencyInfo;
import com.azalea.cathayassignment.domain.dto.TimeInfo;
import com.azalea.cathayassignment.domain.entities.CurrencyEntity;
import com.azalea.cathayassignment.services.CurrencyService;
import com.azalea.cathayassignment.services.CurrentPriceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CurrenPriceControllerTests {
    private MockMvc mockMvc;
    private CurrencyService currencyService;
    private CurrentPriceService currentPriceService;

    @Autowired
    public CurrenPriceControllerTests(MockMvc mockMvc, CurrencyService currencyService, CurrentPriceService currentPriceService) {
        this.currencyService = currencyService;
        this.mockMvc = mockMvc;
        this.currentPriceService = currentPriceService;
    }

    @Test
    public void testGetCoindeskApi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/current-price"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetCurrentPrice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/current-price"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.time.updated").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.code").value("USD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.zh_code").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.rate_float").isNumber())
                .andDo(MockMvcResultHandlers.print());
    }
}
