package com.aquariux.crypto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.aquariux.crypto.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriceAggregateTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceAggregate.class);
        PriceAggregate priceAggregate1 = new PriceAggregate();
        priceAggregate1.setId(1L);
        PriceAggregate priceAggregate2 = new PriceAggregate();
        priceAggregate2.setId(priceAggregate1.getId());
        assertThat(priceAggregate1).isEqualTo(priceAggregate2);
        priceAggregate2.setId(2L);
        assertThat(priceAggregate1).isNotEqualTo(priceAggregate2);
        priceAggregate1.setId(null);
        assertThat(priceAggregate1).isNotEqualTo(priceAggregate2);
    }
}
