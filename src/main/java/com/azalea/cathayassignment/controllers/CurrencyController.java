package com.azalea.cathayassignment.controllers;

import com.azalea.cathayassignment.domain.dto.CurrencyDto;
import com.azalea.cathayassignment.domain.entities.CurrencyEntity;
import com.azalea.cathayassignment.mappers.Mapper;
import com.azalea.cathayassignment.services.CurrencyService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping(path = "/currency")
    public List<CurrencyDto> listCurrencies() {
        List<CurrencyEntity> currencies = currencyService.findAll();
        return currencies.stream()
                .map(currencyMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/currency/{code}")
    public ResponseEntity<CurrencyDto> getCurrency(@PathVariable("code") String code) {
        Optional<CurrencyEntity> foundCurrency = currencyService.findOne(code);
        return foundCurrency.map(currencyEntity -> {
            CurrencyDto currencyDto = currencyMapper.mapTo(currencyEntity);
            return new ResponseEntity<>(currencyDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping(path = "/currency")
    public ResponseEntity<CurrencyDto> createCurrency(@RequestBody CurrencyDto currency) {
        CurrencyEntity currencyEntity = currencyMapper.mapFrom(currency);
        CurrencyEntity savedCurrencyEntity = currencyService.createCurrency(currencyEntity);
        return new ResponseEntity<>(currencyMapper.mapTo(savedCurrencyEntity), HttpStatus.CREATED);
    }

//    @PutMapping(path = "/currency/{code}")
//    public ResponseEntity<CurrencyDto> updateCurrency(@PathVariable("code") String code) {
//
//    }
}
