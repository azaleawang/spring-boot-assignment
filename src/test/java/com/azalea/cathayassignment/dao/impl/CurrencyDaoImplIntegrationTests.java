package com.azalea.cathayassignment.dao.impl;

import com.azalea.cathayassignment.TestDataUtil;
import com.azalea.cathayassignment.domain.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CurrencyDaoImplIntegrationTests {
    @InjectMocks
    private CurrencyDaoImpl underTest;

    @Autowired
    public CurrencyDaoImplIntegrationTests(CurrencyDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testCurrencyCreatedAndRecalled() {
        Currency currency = TestDataUtil.createTestCurrency();
        underTest.create(currency);
        Optional<Currency> result = underTest.findOne(currency.getCode());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(currency);
    }

    @Test
    public void testCurrencyUpdated() {
        Currency currency = TestDataUtil.createTestCurrency();
        underTest.create(currency);
        currency.setZh_code("位元幣");
        underTest.update(currency);
        Optional<Currency> result = underTest.findOne(currency.getCode());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(currency);
    }

    @Test
    public void testCurrencyDeleted() {
        Currency currency = TestDataUtil.createTestCurrency();
        underTest.create(currency);
        underTest.delete(currency.getCode());
        Optional<Currency> result = underTest.findOne(currency.getCode());
        assertThat(result).isEmpty();
    }


}
