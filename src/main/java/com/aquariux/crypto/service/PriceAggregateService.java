package com.aquariux.crypto.service;

import com.aquariux.crypto.domain.PriceAggregate;
import com.aquariux.crypto.repository.PriceAggregateRepository;
import com.aquariux.crypto.service.dto.PriceAggregateDTO;
import com.aquariux.crypto.service.mapper.PriceAggregateMapper;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PriceAggregate}.
 */
@Service
@Transactional
public class PriceAggregateService {

    private final Logger log = LoggerFactory.getLogger(PriceAggregateService.class);

    private final PriceAggregateRepository priceAggregateRepository;

    private final APIBinanceCryptoClient apiBinanceCryptoClient;

    private final PriceAggregateMapper priceAggregateMapper;

    public PriceAggregateService(
        PriceAggregateRepository priceAggregateRepository,
        PriceAggregateMapper priceAggregateMapper,
        APIBinanceCryptoClient apiBinanceCryptoClient
    ) {
        this.priceAggregateRepository = priceAggregateRepository;
        this.priceAggregateMapper = priceAggregateMapper;
        this.apiBinanceCryptoClient = apiBinanceCryptoClient;
    }

    /**
     * Save a priceAggregate.
     *
     * @param priceAggregateDTO the entity to save.
     * @return the persisted entity.
     */
    public PriceAggregateDTO save(PriceAggregateDTO priceAggregateDTO) {
        log.debug("Request to save PriceAggregate : {}", priceAggregateDTO);
        List<PriceAggregateDTO> priceAggregateBinance = apiBinanceCryptoClient.retrieveBinanceTicker();
        PriceAggregate priceAggregate = priceAggregateMapper.toEntity(priceAggregateBinance.get(0));
        priceAggregate = priceAggregateRepository.save(priceAggregate);
        return priceAggregateMapper.toDto(priceAggregate);
    }

    /**
     * Update a priceAggregate.
     *
     * @param priceAggregateDTO the entity to save.
     * @return the persisted entity.
     */
    public PriceAggregateDTO update(PriceAggregateDTO priceAggregateDTO) {
        log.debug("Request to update PriceAggregate : {}", priceAggregateDTO);
        PriceAggregate priceAggregate = priceAggregateMapper.toEntity(priceAggregateDTO);
        priceAggregate = priceAggregateRepository.save(priceAggregate);
        return priceAggregateMapper.toDto(priceAggregate);
    }

    /**
     * Partially update a priceAggregate.
     *
     * @param priceAggregateDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PriceAggregateDTO> partialUpdate(PriceAggregateDTO priceAggregateDTO) {
        log.debug("Request to partially update PriceAggregate : {}", priceAggregateDTO);

        return priceAggregateRepository
            .findById(priceAggregateDTO.getId())
            .map(existingPriceAggregate -> {
                priceAggregateMapper.partialUpdate(existingPriceAggregate, priceAggregateDTO);

                return existingPriceAggregate;
            })
            .map(priceAggregateRepository::save)
            .map(priceAggregateMapper::toDto);
    }

    /**
     * Get all the priceAggregates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PriceAggregateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PriceAggregates");
        return priceAggregateRepository.findAll(pageable).map(priceAggregateMapper::toDto);
    }

    /**
     * Get one priceAggregate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PriceAggregateDTO> findOne(Long id) {
        log.debug("Request to get PriceAggregate : {}", id);
        return priceAggregateRepository.findById(id).map(priceAggregateMapper::toDto);
    }

    /**
     * Delete the priceAggregate by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PriceAggregate : {}", id);
        priceAggregateRepository.deleteById(id);
    }
}
