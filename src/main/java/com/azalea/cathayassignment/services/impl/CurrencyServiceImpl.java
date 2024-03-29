package com.azalea.cathayassignment.services.impl;

import com.azalea.cathayassignment.domain.entities.CurrencyEntity;
import com.azalea.cathayassignment.repositories.CurrencyRepository;
import com.azalea.cathayassignment.services.CurrencyService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public CurrencyEntity save(CurrencyEntity currencyEntity) {
        return currencyRepository.save(currencyEntity);
    }

    @Override
    public List<CurrencyEntity> findAll() {
        return StreamSupport.stream(currencyRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CurrencyEntity> findOne(String code) {
        return currencyRepository.findByCode(code);
    }

    @Override
    @Transactional
    public void delete(String code) {
        currencyRepository.deleteByCode(code);
    }

}
