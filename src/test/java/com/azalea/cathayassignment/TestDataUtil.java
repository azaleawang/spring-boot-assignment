package com.azalea.cathayassignment;

import com.azalea.cathayassignment.domain.Currency;

public final class TestDataUtil {
    private TestDataUtil() {

    }


    public static Currency createTestCurrency() {
        return Currency.builder()
                .code("Bitcoin").zh_code("比特幣").build();
    }
}
