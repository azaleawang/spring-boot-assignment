package com.azalea.cathayassignment.controllers;

import com.azalea.cathayassignment.TestDataUtil;
import com.azalea.cathayassignment.domain.entities.CurrencyEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CurrencyControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public CurrencyControllerIntegrationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateCurrencySuccessfullyReturnHttp201() throws Exception {
        CurrencyEntity testCurrency = TestDataUtil.createTestCurrency();
        testCurrency.setId(null);
        String currencyJson = objectMapper.writeValueAsString(testCurrency);
        mockMvc.perform(MockMvcRequestBuilders.post("/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(currencyJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }


    @Test
    public void testCreateCurrencySuccessfullyReturnSavedCurrency() throws Exception {
        CurrencyEntity testCurrency = TestDataUtil.createTestCurrency();
        testCurrency.setId(null);
        String currencyJson = objectMapper.writeValueAsString(testCurrency);
        mockMvc.perform(MockMvcRequestBuilders.post("/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(currencyJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.code").value("Bitcoin")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.zh_code").value("比特幣")
        );
    }
}
