package com.azalea.cathayassignment;

import com.azalea.cathayassignment.domain.entities.CurrencyEntity;

public final class TestDataUtil {
    private TestDataUtil() {

    }


    public static CurrencyEntity createTestCurrency() {
        return CurrencyEntity.builder()
                .code("Bitcoin").zh_code("比特幣").build();
    }
}
