package com.azalea.cathayassignment.controllers;

import com.azalea.cathayassignment.domain.dto.CoinDeskResponse;
import com.azalea.cathayassignment.services.CurrentPriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentPriceController {

    private final CurrentPriceService currentPriceService;

    public CurrentPriceController(CurrentPriceService currentPriceService) {
        this.currentPriceService = currentPriceService;
    }

    @GetMapping(path = "/current-price")
    public CoinDeskResponse getCurrentPrice() {
        CoinDeskResponse priceResp = currentPriceService.getCurrentPrice();
        return currentPriceService.transformData(priceResp);
    }
}
