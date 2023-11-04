package com.aquariux.crypto.scheduling;

import com.aquariux.crypto.repository.CountryRepository;
import com.aquariux.crypto.service.APIBinanceCryptoClient;
import com.aquariux.crypto.service.dto.PriceAggregateDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private long delay = 0;

    private APIBinanceCryptoClient apiBinanceCryptoClient;

    public TestService(APIBinanceCryptoClient countryRepository) {
        this.apiBinanceCryptoClient = countryRepository;
    }

    public long getDelay() {
        this.delay += 1000;
        System.out.println("Price Aggregation delaying " + this.delay + " milliseconds...");
        return this.delay;
    }

    public void tick() {
        final long now = System.currentTimeMillis() / 1000;
        List<PriceAggregateDTO> priceAggregateDTO = apiBinanceCryptoClient.retrieveBinanceTicker();
        System.out.println("Price Aggregation schedule tasks with dynamic delay - priceAggregateDTO " + priceAggregateDTO.get(0));
        System.out.println("Price Aggregation schedule tasks with dynamic delay - " + now);
    }
}
