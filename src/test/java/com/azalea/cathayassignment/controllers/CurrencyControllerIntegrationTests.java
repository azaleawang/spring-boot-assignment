package com.azalea.cathayassignment.controllers;

import com.azalea.cathayassignment.TestDataUtil;
import com.azalea.cathayassignment.domain.dto.CurrencyDto;
import com.azalea.cathayassignment.domain.entities.CurrencyEntity;
import com.azalea.cathayassignment.services.CurrencyService;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CurrencyControllerIntegrationTests {
    private CurrencyService currencyService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public CurrencyControllerIntegrationTests(MockMvc mockMvc, CurrencyService currencyService) {
        this.currencyService = currencyService;
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    // 測試呼叫查詢幣別對應表資料API，並顯示其內容: GET成功回應200
    @Test
    public void testListCurrenciesReturnHttp200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/currency")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    // 測試呼叫查詢幣別對應表資料API，並顯示其內容: GET /currency 得到正確資料格式
    @Test
    public void testListCurrenciesReturnListCurrencies() throws Exception {
        CurrencyEntity currencyEntity = TestDataUtil.createTestCurrencyA();
        currencyService.save(currencyEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/currency")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].code").value("Bitcoin")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].zh_code").value("比特幣")
        );
    }

    // 測試呼叫查詢幣別對應表資料API，並顯示其內容: GET /currency/code 成功回應200
    @Test
    public void testGetCurrencyReturnHttp200WhenExist() throws Exception {
        CurrencyEntity currencyEntity = TestDataUtil.createTestCurrencyA();
        currencyService.save(currencyEntity);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/currency/Bitcoin")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()
                );
    }


    // 測試呼叫查詢幣別對應表資料API，並顯示其內容: GET /currency/不存在的code 回應404
    @Test
    public void testGetCurrencyReturnHttp404WhenNotExist() throws Exception {
        CurrencyEntity currencyEntity = TestDataUtil.createTestCurrencyA();
        currencyService.save(currencyEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/currency/abc")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    // 測試呼叫查詢幣別對應表資料API，並顯示其內容: GET /currency/code 得到正確資料格式
    @Test
    public void testGetCurrencyReturnCurrencyWhenExist() throws Exception {
        CurrencyEntity currencyEntity = TestDataUtil.createTestCurrencyA();
        currencyService.save(currencyEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/currency/Bitcoin")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.code").value("Bitcoin")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.zh_code").value("比特幣")
        );
    }


    // 測試呼叫新增幣別對應表資料API: 成功創建回應201
    @Test
    public void testCreateCurrencySuccessfullyReturnHttp201() throws Exception {
        CurrencyEntity testCurrency = TestDataUtil.createTestCurrencyA();
        testCurrency.setId(null);
        String currencyJson = objectMapper.writeValueAsString(testCurrency);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/currency")
                .contentType(MediaType.APPLICATION_JSON)
                .content(currencyJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andDo(
                MockMvcResultHandlers.print()
        );
    }


    // 測試呼叫新增幣別對應表資料API: 成功創建回應正確資料格式
    @Test
    public void testCreateCurrencySuccessfullyReturnSavedCurrency() throws Exception {
        CurrencyEntity testCurrency = TestDataUtil.createTestCurrencyA();
        testCurrency.setId(null);
        String currencyJson = objectMapper.writeValueAsString(testCurrency);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/currency")
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


    // 測試呼叫更新幣別對應表資料API，並顯示其內容。成功PUT 得到200
    @Test
    public void testFullUpdateCurrencyReturnHttp200WhenExist() throws Exception {
        CurrencyEntity testCurrencyEntity = TestDataUtil.createTestCurrencyA();
        CurrencyEntity savedCurrency = currencyService.save(testCurrencyEntity);

        CurrencyDto currencyDto = TestDataUtil.createTestCurrencyDtoA();
        String currencyDtoJson = objectMapper.writeValueAsString(currencyDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/currency/" + savedCurrency.getCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(currencyDtoJson)
        ).andDo(MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    // 測試呼叫更新幣別對應表資料API，並顯示其內容。PUT /currency/不存在的code 得到404
    @Test
    public void testFullUpdateCurrencyReturnHttp404WhenNotExist() throws Exception {
        CurrencyDto testCurrencyDto = TestDataUtil.createTestCurrencyDtoA();
        String currencyDtoJson = objectMapper.writeValueAsString(testCurrencyDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/currency/abc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(currencyDtoJson)
        ).andDo(MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    // 測試呼叫更新幣別對應表資料API，並顯示其內容。檢查更新結果符合預期
    @Test
    public void testFullUpdateUpdatesExistingCurrency() throws Exception {
        CurrencyEntity testCurrencyEntityA = TestDataUtil.createTestCurrencyA();
        CurrencyEntity savedCurrency = currencyService.save(testCurrencyEntityA);

        CurrencyEntity currencyDto = TestDataUtil.createTestCurrencyB();
        currencyDto.setId(currencyDto.getId());
        String currencyDtoUpdateJson = objectMapper.writeValueAsString(currencyDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/currency/" + savedCurrency.getCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(currencyDtoUpdateJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedCurrency.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.code").value(currencyDto.getCode())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.zh_code").value(currencyDto.getZh_code())
        ).andDo(MockMvcResultHandlers.print());
    }

    // 測試呼叫刪除幣別對應表資料API。成功delete 回傳204
    @Test
    public void testDeleteCurrencyReturnHttp204WhenExist() throws Exception {
        CurrencyEntity testCurrencyEntityA = TestDataUtil.createTestCurrencyA();
        CurrencyEntity savedAuthor = currencyService.save(testCurrencyEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/currency/" + savedAuthor.getCode())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent()
        ).andDo(MockMvcResultHandlers.print());
    }

    // 測試呼叫刪除幣別對應表資料API。delete 不存在的項目也能回傳204
    @Test
    public void testDeleteCurrencyReturnHttp204WhenNotExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/currency/" + "asd")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent()
        ).andDo(MockMvcResultHandlers.print());
    }

}
