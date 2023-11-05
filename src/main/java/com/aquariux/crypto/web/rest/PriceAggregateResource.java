package com.aquariux.crypto.web.rest;

import com.aquariux.crypto.repository.PriceAggregateRepository;
import com.aquariux.crypto.service.PriceAggregateService;
import com.aquariux.crypto.service.dto.PriceAggregateDTO;
import com.aquariux.crypto.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.aquariux.crypto.domain.PriceAggregate}.
 */
@RestController
@RequestMapping("/api")
public class PriceAggregateResource {

    private final Logger log = LoggerFactory.getLogger(PriceAggregateResource.class);

    private static final String ENTITY_NAME = "priceAggregate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PriceAggregateService priceAggregateService;

    private final PriceAggregateRepository priceAggregateRepository;

    public PriceAggregateResource(PriceAggregateService priceAggregateService, PriceAggregateRepository priceAggregateRepository) {
        this.priceAggregateService = priceAggregateService;
        this.priceAggregateRepository = priceAggregateRepository;
    }

    /**
     * {@code POST  /price-aggregates} : Create a new priceAggregate.
     *
     * @param priceAggregateDTO the priceAggregateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new priceAggregateDTO, or with status {@code 400 (Bad Request)} if the priceAggregate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/price-aggregates")
    public ResponseEntity<PriceAggregateDTO> createPriceAggregate(@RequestBody PriceAggregateDTO priceAggregateDTO)
        throws URISyntaxException {
        log.debug("REST request to save PriceAggregate : {}", priceAggregateDTO);
        if (priceAggregateDTO.getId() != null) {
            throw new BadRequestAlertException("A new priceAggregate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PriceAggregateDTO result = priceAggregateService.save(priceAggregateDTO);
        return ResponseEntity
            .created(new URI("/api/price-aggregates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /price-aggregates/:id} : Updates an existing priceAggregate.
     *
     * @param id the id of the priceAggregateDTO to save.
     * @param priceAggregateDTO the priceAggregateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceAggregateDTO,
     * or with status {@code 400 (Bad Request)} if the priceAggregateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the priceAggregateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/price-aggregates/{id}")
    public ResponseEntity<PriceAggregateDTO> updatePriceAggregate(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PriceAggregateDTO priceAggregateDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PriceAggregate : {}, {}", id, priceAggregateDTO);
        if (priceAggregateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, priceAggregateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!priceAggregateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PriceAggregateDTO result = priceAggregateService.update(priceAggregateDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, priceAggregateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /price-aggregates/:id} : Partial updates given fields of an existing priceAggregate, field will ignore if it is null
     *
     * @param id the id of the priceAggregateDTO to save.
     * @param priceAggregateDTO the priceAggregateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceAggregateDTO,
     * or with status {@code 400 (Bad Request)} if the priceAggregateDTO is not valid,
     * or with status {@code 404 (Not Found)} if the priceAggregateDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the priceAggregateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/price-aggregates/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PriceAggregateDTO> partialUpdatePriceAggregate(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PriceAggregateDTO priceAggregateDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PriceAggregate partially : {}, {}", id, priceAggregateDTO);
        if (priceAggregateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, priceAggregateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!priceAggregateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PriceAggregateDTO> result = priceAggregateService.partialUpdate(priceAggregateDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, priceAggregateDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /price-aggregates} : get all the priceAggregates.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of priceAggregates in body.
     */
    @GetMapping("/price-aggregates")
    public ResponseEntity<List<PriceAggregateDTO>> getAllPriceAggregates(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of PriceAggregates");
        Page<PriceAggregateDTO> page = priceAggregateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /price-aggregates/:id} : get the "id" priceAggregate.
     *
     * @param id the id of the priceAggregateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the priceAggregateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/price-aggregates/{id}")
    public ResponseEntity<PriceAggregateDTO> getPriceAggregate(@PathVariable Long id) {
        log.debug("REST request to get PriceAggregate : {}", id);
        Optional<PriceAggregateDTO> priceAggregateDTO = priceAggregateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(priceAggregateDTO);
    }

    /**
     * {@code DELETE  /price-aggregates/:id} : delete the "id" priceAggregate.
     *
     * @param id the id of the priceAggregateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/price-aggregates/{id}")
    public ResponseEntity<Void> deletePriceAggregate(@PathVariable Long id) {
        log.debug("REST request to delete PriceAggregate : {}", id);
        priceAggregateService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
