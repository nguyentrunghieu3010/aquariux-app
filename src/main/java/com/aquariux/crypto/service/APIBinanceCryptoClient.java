package com.aquariux.crypto.service;

import com.aquariux.crypto.service.dto.PriceAggregateDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "BINANCE-TICKER", url = "https://api.binance.com")
public interface APIBinanceCryptoClient {
    @GetMapping(value = "/api/v3/ticker/bookTicker")
    List<PriceAggregateDTO> retrieveBinanceTicker();
}
