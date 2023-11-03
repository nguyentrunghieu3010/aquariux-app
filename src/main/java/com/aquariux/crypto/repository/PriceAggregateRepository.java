package com.aquariux.crypto.repository;

import com.aquariux.crypto.domain.PriceAggregate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PriceAggregate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriceAggregateRepository extends JpaRepository<PriceAggregate, Long> {}
