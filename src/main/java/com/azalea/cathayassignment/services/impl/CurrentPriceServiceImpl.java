package com.azalea.cathayassignment.services.impl;

import com.azalea.cathayassignment.domain.dto.CoinDeskResponse;
import com.azalea.cathayassignment.domain.entities.CurrencyEntity;
import com.azalea.cathayassignment.services.CurrencyService;
import com.azalea.cathayassignment.services.CurrentPriceService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
public class CurrentPriceServiceImpl implements CurrentPriceService {
    private final String COINDESK_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private final CurrencyService currencyService;

    public CurrentPriceServiceImpl(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public CoinDeskResponse getCurrentPrice() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(COINDESK_URL, CoinDeskResponse.class);
    }

    @Override
    public CoinDeskResponse transformData(CoinDeskResponse response) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss 'UTC'", Locale.ENGLISH);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = inputFormat.parse(response.getTime().getUpdated());
            response.getTime().setUpdated(outputFormat.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Add zh_code for each currency
        response.getBpi().forEach((key, value) -> {
            Optional<CurrencyEntity> foundCurrency = currencyService.findOne(key);
            if (!foundCurrency.isPresent()) {
                value.setZhCode("");
            } else {
                value.setZhCode(foundCurrency.get().getZh_code());
            }
        });

        return response;
    }
}
