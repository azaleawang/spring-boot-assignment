package com.azalea.cathayassignment.services;

import com.azalea.cathayassignment.domain.entities.CurrencyEntity;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    CurrencyEntity createCurrency(CurrencyEntity currencyEntity);

    List<CurrencyEntity> findAll();

    Optional<CurrencyEntity> findOne(String code);
}
