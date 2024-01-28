package com.azalea.cathayassignment.dao.impl;

import com.azalea.cathayassignment.TestDataUtil;
import com.azalea.cathayassignment.domain.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CurrencyDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CurrencyDaoImpl underTest;

    @Test
    public void testCreateCurrencyGenerateCorrectSql() {
        Currency currency = TestDataUtil.createTestCurrency();
        underTest.create(currency);

        verify(jdbcTemplate).update(
                eq("INSERT INTO currency (code, zh_code) VALUES (?, ?)"),
                eq("Bitcoin"),
                eq("比特幣")
        );
    }

    @Test
    public void testFindOneGenerateCorrectSql(){
        underTest.findOne("Bitcoin");
        verify(jdbcTemplate).query(
                eq("SELECT code, zh_code FROM currency WHERE code = ? LIMIT 1"),
                ArgumentMatchers.<CurrencyDaoImpl.CurrencyRowMapper>any(),
                eq("Bitcoin")
        );
    }

    @Test
    public void testUpdateGenerateCorrectSql() {
        Currency currency = TestDataUtil.createTestCurrency();
        underTest.update(currency);

        verify(jdbcTemplate).update(
                "UPDATE currency SET code = ?, zh_code = ? WHERE code = ?",
                "Bitcoin", "比特幣", "Bitcoin"
        );
    }

    @Test
    public void testDeleteGenerateCorrectSql() {
        underTest.delete("Bitcoin");
        verify(jdbcTemplate).update(
                "DELETE FROM currency WHERE CODE = ?",
                "Bitcoin"
        );
    }
}
