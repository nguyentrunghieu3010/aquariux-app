package com.aquariux.crypto.service.mapper;

import com.aquariux.crypto.domain.PriceAggregate;
import com.aquariux.crypto.service.dto.PriceAggregateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PriceAggregate} and its DTO {@link PriceAggregateDTO}.
 */
@Mapper(componentModel = "spring")
public interface PriceAggregateMapper extends EntityMapper<PriceAggregateDTO, PriceAggregate> {}
