package com.azalea.cathayassignment.services;

import com.azalea.cathayassignment.domain.dto.CoinDeskResponse;

import java.util.List;

public interface CurrentPriceService {
    CoinDeskResponse getCurrentPrice();

    CoinDeskResponse transformData(CoinDeskResponse priceResp);
}
