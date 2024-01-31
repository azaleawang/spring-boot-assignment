package com.azalea.cathayassignment.controllers;

import com.azalea.cathayassignment.services.CurrencyService;
import com.azalea.cathayassignment.services.CurrentPriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CurrenPriceControllerIntegrationTests {
    private MockMvc mockMvc;
    private CurrencyService currencyService;
    private CurrentPriceService currentPriceService;

    @Autowired
    public CurrenPriceControllerIntegrationTests(MockMvc mockMvc, CurrencyService currencyService, CurrentPriceService currentPriceService) {
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
