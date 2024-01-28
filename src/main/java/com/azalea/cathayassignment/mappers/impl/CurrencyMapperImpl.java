package com.azalea.cathayassignment.mappers.impl;

import com.azalea.cathayassignment.domain.dto.CurrencyDto;
import com.azalea.cathayassignment.domain.entities.CurrencyEntity;
import com.azalea.cathayassignment.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapperImpl implements Mapper<CurrencyEntity, CurrencyDto> {

    private ModelMapper modelMapper;

    public CurrencyMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CurrencyDto mapTo(CurrencyEntity currencyEntity) {
        return modelMapper.map(currencyEntity, CurrencyDto.class);
    }

    @Override
    public CurrencyEntity mapFrom(CurrencyDto currencyDto) {
        return modelMapper.map(currencyDto, CurrencyEntity.class);
    }
}