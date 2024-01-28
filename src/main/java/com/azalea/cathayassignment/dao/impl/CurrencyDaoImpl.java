package com.azalea.cathayassignment.dao.impl;

import com.azalea.cathayassignment.dao.CurrencyDao;
import com.azalea.cathayassignment.domain.Currency;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class CurrencyDaoImpl implements CurrencyDao {

    private final JdbcTemplate jdbcTemplate;

    public CurrencyDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void create(Currency currency) {
        jdbcTemplate.update("INSERT INTO currency (code, zh_code) VALUES (?, ?)",
                currency.getCode(), currency.getZh_code()
        );
    }

    @Override
    public Optional<Currency> findOne(String code) {
        List<Currency> results = jdbcTemplate.query(
                "SELECT code, zh_code FROM currency WHERE code = ? LIMIT 1",
                new CurrencyRowMapper(),
                code);

        return results.stream().findFirst();
    }

    @Override
    public void update(Currency currency) {
        jdbcTemplate.update("UPDATE currency SET code = ?, zh_code = ? WHERE code = ?",
                currency.getCode(), currency.getZh_code(), currency.getCode()
        );
    }

    @Override
    public void delete(String code) {
        jdbcTemplate.update("DELETE FROM currency WHERE CODE = ?", code);
    }

    public static class CurrencyRowMapper implements RowMapper<Currency> {

        @Override
        public Currency mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Currency.builder()
                    .code(rs.getString("code"))
                    .zh_code(rs.getString("zh_code"))
                    .build();
        }
    }

}
