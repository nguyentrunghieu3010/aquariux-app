package com.aquariux.crypto.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.aquariux.crypto.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriceAggregateDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceAggregateDTO.class);
        PriceAggregateDTO priceAggregateDTO1 = new PriceAggregateDTO();
        priceAggregateDTO1.setId(1L);
        PriceAggregateDTO priceAggregateDTO2 = new PriceAggregateDTO();
        assertThat(priceAggregateDTO1).isNotEqualTo(priceAggregateDTO2);
        priceAggregateDTO2.setId(priceAggregateDTO1.getId());
        assertThat(priceAggregateDTO1).isEqualTo(priceAggregateDTO2);
        priceAggregateDTO2.setId(2L);
        assertThat(priceAggregateDTO1).isNotEqualTo(priceAggregateDTO2);
        priceAggregateDTO1.setId(null);
        assertThat(priceAggregateDTO1).isNotEqualTo(priceAggregateDTO2);
    }
}
