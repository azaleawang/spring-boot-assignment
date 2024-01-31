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
        // TODO write test for 404
        Optional<CurrencyEntity> foundCurrency = currencyService.findOne(currencyEntity.getCode());
        if (foundCurrency.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CurrencyEntity savedCurrencyEntity = currencyService.save(currencyEntity);
        return new ResponseEntity<>(currencyMapper.mapTo(savedCurrencyEntity), HttpStatus.CREATED);
    }

    @PutMapping(path = "/currency/{code}")
    public ResponseEntity<CurrencyDto> fullUpdateCurrency(
            @PathVariable("code") String code,
            @RequestBody CurrencyDto currencyDto) {

        Optional<CurrencyEntity> foundCurrency = currencyService.findOne(code);
        if (!foundCurrency.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currencyDto.setId(foundCurrency.get().getId());
        CurrencyEntity currencyEntity = currencyMapper.mapFrom(currencyDto);
        CurrencyEntity savedCurrencyEntity = currencyService.save(currencyEntity);
        return new ResponseEntity<>(
                currencyMapper.mapTo(savedCurrencyEntity),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/currency/{code}")
    public ResponseEntity deleteCurrency(@PathVariable("code") String code) {
        currencyService.delete(code);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
