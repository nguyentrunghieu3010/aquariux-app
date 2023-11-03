package com.aquariux.crypto.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriceAggregateMapperTest {

    private PriceAggregateMapper priceAggregateMapper;

    @BeforeEach
    public void setUp() {
        priceAggregateMapper = new PriceAggregateMapperImpl();
    }
}
