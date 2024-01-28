package com.azalea.cathayassignment.repositories;

import com.azalea.cathayassignment.TestDataUtil;
import com.azalea.cathayassignment.domain.entities.CurrencyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CurrencyEntityRepositoryIntegrationTests {
    private CurrencyRepository underTest;

    @Autowired
    public CurrencyEntityRepositoryIntegrationTests(CurrencyRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testCurrencyCreatedAndRecalled() {
        CurrencyEntity currencyEntity = TestDataUtil.createTestCurrency();
        underTest.save(currencyEntity);
        Optional<CurrencyEntity> result = underTest.findById(currencyEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(currencyEntity);
    }

    @Test
    public void testCurrencyUpdated() {
        CurrencyEntity currencyEntity = TestDataUtil.createTestCurrency();
        underTest.save(currencyEntity);
        currencyEntity.setZh_code("位元幣");
        underTest.save(currencyEntity);
        Optional<CurrencyEntity> result = underTest.findById(currencyEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(currencyEntity);
    }

    @Test
    public void testCurrencyDeleted() {
        CurrencyEntity currencyEntity = TestDataUtil.createTestCurrency();
        underTest.save(currencyEntity);
        underTest.deleteById(currencyEntity.getId());
        Optional<CurrencyEntity> result = underTest.findById(currencyEntity.getId());
        assertThat(result).isEmpty();
    }

}
