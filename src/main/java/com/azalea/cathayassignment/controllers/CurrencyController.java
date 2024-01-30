package com.azalea.cathayassignment.controllers;

import com.azalea.cathayassignment.domain.dto.CurrencyDto;
import com.azalea.cathayassignment.domain.entities.CurrencyEntity;
import com.azalea.cathayassignment.mappers.Mapper;
import com.azalea.cathayassignment.services.CurrencyService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
public class CurrencyController {

    private CurrencyService currencyService;

    private Mapper<CurrencyEntity, CurrencyDto> currencyMapper;
    public CurrencyController(CurrencyService currencyService, Mapper<CurrencyEntity, CurrencyDto> currencyMapper) {
        this.currencyService = currencyService;
        this.currencyMapper = currencyMapper;
    }
//    @GetMapping(path = "/price")
//    public Currency retrievePrice() {
//        return Currency.builder()
//                .id(1L)
//                .code("Bitcoin")
//                .zh_code("比特幣")
//                .build();
//    }

    @PostMapping(path = "/currency")
    public ResponseEntity<CurrencyDto> createCurrency(@RequestBody CurrencyDto currency) {
        CurrencyEntity currencyEntity = currencyMapper.mapFrom(currency);
        CurrencyEntity savedCurrencyEntity = currencyService.createCurrency(currencyEntity);
        return new ResponseEntity<>(currencyMapper.mapTo(savedCurrencyEntity), HttpStatus.CREATED);
    }

    @PutMapping(path = "/currency/{code}")
    public ResponseEntity<CurrencyEntity> updateCurrency(@PathVariable("code") String code) {

    }
}
