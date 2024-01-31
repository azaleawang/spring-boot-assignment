package com.azalea.cathayassignment.services.impl;

import com.azalea.cathayassignment.domain.dto.CoinDeskResponse;
import com.azalea.cathayassignment.services.CurrentPriceService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class CurrentPriceServiceImpl implements CurrentPriceService {
    private final String COINDESK_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
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
        Map<String, String> zhCodeMap = new HashMap<>();
        zhCodeMap.put("USD", "美元");
        zhCodeMap.put("GBP", "英鎊");
        zhCodeMap.put("EUR", "歐元");

        response.getBpi().forEach((key, value) -> value.setZhCode(zhCodeMap.get(key)));

        return response;
    }
}
