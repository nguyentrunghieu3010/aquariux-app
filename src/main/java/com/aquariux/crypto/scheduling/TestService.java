package com.aquariux.crypto.scheduling;

import com.aquariux.crypto.service.APIBinanceCryptoClient;
import com.aquariux.crypto.service.PriceAggregateService;
import com.aquariux.crypto.service.dto.PriceAggregateDTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private long delay = 0;

    //    private final PriceAggregateService priceAggregateService;
    private final APIBinanceCryptoClient apiBinanceCryptoClient;

    public TestService(APIBinanceCryptoClient apiBinanceCryptoClient) {
        //        this.priceAggregateService = priceAggregateService;
        this.apiBinanceCryptoClient = apiBinanceCryptoClient;
    }

    public long getDelay() {
        this.delay += 1000;
        System.out.println("Price Aggregation delaying " + this.delay + " milliseconds...");
        return this.delay;
    }

    public void tick() {
        final long now = System.currentTimeMillis() / 1000;
        List<PriceAggregateDTO> binanceCryptoResponse = apiBinanceCryptoClient.retrieveBinanceTicker();

        for (PriceAggregateDTO aggregateDTO : binanceCryptoResponse) {
            System.out.println("Price Aggregation schedule tasks with dynamic delay - Binance Crypto Data " + aggregateDTO);
            //            priceAggregateService.save(aggregateDTO);
        }
        System.out.println("Price Aggregation schedule tasks with dynamic delay - " + now);
    }
}
