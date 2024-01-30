package com.azalea.cathayassignment;

import com.azalea.cathayassignment.domain.dto.CurrencyDto;
import com.azalea.cathayassignment.domain.entities.CurrencyEntity;

public final class TestDataUtil {
    private TestDataUtil() {

    }


    public static CurrencyEntity createTestCurrencyA() {
        return CurrencyEntity.builder()
                .code("Bitcoin").zh_code("比特幣").build();
    }

    public static CurrencyEntity createTestCurrencyB() {
        return CurrencyEntity.builder()
                .code("USD").zh_code("美元").build();
    }

    public static CurrencyDto createTestCurrencyDtoA() {
        return CurrencyDto.builder()
                .id(1L)
                .code("Bitcoin")
                .zh_code("比特幣")
                .build();
    }

    public static CurrencyDto createTestCurrencyDtoB() {
        return CurrencyDto.builder()
                .id(2L)
                .code("USD")
                .zh_code("美元")
                .build();
    }
}
