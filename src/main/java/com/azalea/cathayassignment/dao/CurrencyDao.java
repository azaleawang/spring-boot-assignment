package com.azalea.cathayassignment.dao;

import com.azalea.cathayassignment.domain.Currency;

import java.util.Optional;

public interface CurrencyDao {
    void create(Currency currency);
    Optional<Currency> findOne(String code);

    void update(Currency currency);

    void delete(String code);
}
