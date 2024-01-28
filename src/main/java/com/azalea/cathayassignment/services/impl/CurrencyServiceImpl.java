package com.azalea.cathayassignment.services.impl;

import com.azalea.cathayassignment.domain.entities.CurrencyEntity;
import com.azalea.cathayassignment.repositories.CurrencyRepository;
import com.azalea.cathayassignment.services.CurrencyService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public CurrencyEntity createCurrency(CurrencyEntity currencyEntity) {
        return currencyRepository.save(currencyEntity);
    }
}
