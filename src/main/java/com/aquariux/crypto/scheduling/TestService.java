package com.aquariux.crypto.scheduling;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    private long delay = 0;

    public long getDelay() {
        this.delay += 1000;
        System.out.println("Price Aggregation delaying " + this.delay + " milliseconds...");
        return this.delay;
    }

    public void tick() {
        final long now = System.currentTimeMillis() / 1000;
        System.out.println("Price Aggregation schedule tasks with dynamic delay - " + now);
    }
}
