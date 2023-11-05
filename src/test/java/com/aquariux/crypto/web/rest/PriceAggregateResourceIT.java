package com.aquariux.crypto.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.aquariux.crypto.IntegrationTest;
import com.aquariux.crypto.domain.PriceAggregate;
import com.aquariux.crypto.domain.enumeration.SourceType;
import com.aquariux.crypto.repository.PriceAggregateRepository;
import com.aquariux.crypto.service.dto.PriceAggregateDTO;
import com.aquariux.crypto.service.mapper.PriceAggregateMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PriceAggregateResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PriceAggregateResourceIT {

    private static final String DEFAULT_SYMBOL_CRYPTO = "AAAAAAAAAA";
    private static final String UPDATED_SYMBOL_CRYPTO = "BBBBBBBBBB";

    private static final String DEFAULT_SYMBOL = "AAAAAAAAAA";
    private static final String UPDATED_SYMBOL = "BBBBBBBBBB";

    private static final Double DEFAULT_BID_PRICE = 1D;
    private static final Double UPDATED_BID_PRICE = 2D;

    private static final Double DEFAULT_BID_QTY = 1D;
    private static final Double UPDATED_BID_QTY = 2D;

    private static final Double DEFAULT_ASK_PRICE = 1D;
    private static final Double UPDATED_ASK_PRICE = 2D;

    private static final Double DEFAULT_ASK_QTY = 1D;
    private static final Double UPDATED_ASK_QTY = 2D;

    private static final Double DEFAULT_BID = 1D;
    private static final Double UPDATED_BID = 2D;

    private static final Double DEFAULT_BID_SIZE = 1D;
    private static final Double UPDATED_BID_SIZE = 2D;

    private static final Double DEFAULT_ASK = 1D;
    private static final Double UPDATED_ASK = 2D;

    private static final Double DEFAULT_ASK_SIZE = 1D;
    private static final Double UPDATED_ASK_SIZE = 2D;

    private static final SourceType DEFAULT_SOURCE_TYPE = SourceType.BINANCE;
    private static final SourceType UPDATED_SOURCE_TYPE = SourceType.HUOBI;

    private static final String ENTITY_API_URL = "/api/price-aggregates";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PriceAggregateRepository priceAggregateRepository;

    @Autowired
    private PriceAggregateMapper priceAggregateMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPriceAggregateMockMvc;

    private PriceAggregate priceAggregate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceAggregate createEntity(EntityManager em) {
        PriceAggregate priceAggregate = new PriceAggregate()
            .symbolCrypto(DEFAULT_SYMBOL_CRYPTO)
            .symbol(DEFAULT_SYMBOL)
            .bidPrice(DEFAULT_BID_PRICE)
            .bidQty(DEFAULT_BID_QTY)
            .askPrice(DEFAULT_ASK_PRICE)
            .askQty(DEFAULT_ASK_QTY)
            .bid(DEFAULT_BID)
            .bidSize(DEFAULT_BID_SIZE)
            .ask(DEFAULT_ASK)
            .askSize(DEFAULT_ASK_SIZE)
            .sourceType(DEFAULT_SOURCE_TYPE);
        return priceAggregate;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceAggregate createUpdatedEntity(EntityManager em) {
        PriceAggregate priceAggregate = new PriceAggregate()
            .symbolCrypto(UPDATED_SYMBOL_CRYPTO)
            .symbol(UPDATED_SYMBOL)
            .bidPrice(UPDATED_BID_PRICE)
            .bidQty(UPDATED_BID_QTY)
            .askPrice(UPDATED_ASK_PRICE)
            .askQty(UPDATED_ASK_QTY)
            .bid(UPDATED_BID)
            .bidSize(UPDATED_BID_SIZE)
            .ask(UPDATED_ASK)
            .askSize(UPDATED_ASK_SIZE)
            .sourceType(UPDATED_SOURCE_TYPE);
        return priceAggregate;
    }

    @BeforeEach
    public void initTest() {
        priceAggregate = createEntity(em);
    }

    @Test
    @Transactional
    void createPriceAggregate() throws Exception {
        int databaseSizeBeforeCreate = priceAggregateRepository.findAll().size();
        // Create the PriceAggregate
        PriceAggregateDTO priceAggregateDTO = priceAggregateMapper.toDto(priceAggregate);
        restPriceAggregateMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(priceAggregateDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PriceAggregate in the database
        List<PriceAggregate> priceAggregateList = priceAggregateRepository.findAll();
        assertThat(priceAggregateList).hasSize(databaseSizeBeforeCreate + 1);
        PriceAggregate testPriceAggregate = priceAggregateList.get(priceAggregateList.size() - 1);
        assertThat(testPriceAggregate.getSymbolCrypto()).isEqualTo(DEFAULT_SYMBOL_CRYPTO);
        assertThat(testPriceAggregate.getSymbol()).isEqualTo(DEFAULT_SYMBOL);
        assertThat(testPriceAggregate.getBidPrice()).isEqualTo(DEFAULT_BID_PRICE);
        assertThat(testPriceAggregate.getBidQty()).isEqualTo(DEFAULT_BID_QTY);
        assertThat(testPriceAggregate.getAskPrice()).isEqualTo(DEFAULT_ASK_PRICE);
        assertThat(testPriceAggregate.getAskQty()).isEqualTo(DEFAULT_ASK_QTY);
        assertThat(testPriceAggregate.getBid()).isEqualTo(DEFAULT_BID);
        assertThat(testPriceAggregate.getBidSize()).isEqualTo(DEFAULT_BID_SIZE);
        assertThat(testPriceAggregate.getAsk()).isEqualTo(DEFAULT_ASK);
        assertThat(testPriceAggregate.getAskSize()).isEqualTo(DEFAULT_ASK_SIZE);
        assertThat(testPriceAggregate.getSourceType()).isEqualTo(DEFAULT_SOURCE_TYPE);
    }

    @Test
    @Transactional
    void createPriceAggregateWithExistingId() throws Exception {
        // Create the PriceAggregate with an existing ID
        priceAggregate.setId(1L);
        PriceAggregateDTO priceAggregateDTO = priceAggregateMapper.toDto(priceAggregate);

        int databaseSizeBeforeCreate = priceAggregateRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPriceAggregateMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(priceAggregateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceAggregate in the database
        List<PriceAggregate> priceAggregateList = priceAggregateRepository.findAll();
        assertThat(priceAggregateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPriceAggregates() throws Exception {
        // Initialize the database
        priceAggregateRepository.saveAndFlush(priceAggregate);

        // Get all the priceAggregateList
        restPriceAggregateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(priceAggregate.getId().intValue())))
            .andExpect(jsonPath("$.[*].symbolCrypto").value(hasItem(DEFAULT_SYMBOL_CRYPTO)))
            .andExpect(jsonPath("$.[*].symbol").value(hasItem(DEFAULT_SYMBOL)))
            .andExpect(jsonPath("$.[*].bidPrice").value(hasItem(DEFAULT_BID_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].bidQty").value(hasItem(DEFAULT_BID_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].askPrice").value(hasItem(DEFAULT_ASK_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].askQty").value(hasItem(DEFAULT_ASK_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].bid").value(hasItem(DEFAULT_BID.doubleValue())))
            .andExpect(jsonPath("$.[*].bidSize").value(hasItem(DEFAULT_BID_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].ask").value(hasItem(DEFAULT_ASK.doubleValue())))
            .andExpect(jsonPath("$.[*].askSize").value(hasItem(DEFAULT_ASK_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].sourceType").value(hasItem(DEFAULT_SOURCE_TYPE.toString())));
    }

    @Test
    @Transactional
    void getPriceAggregate() throws Exception {
        // Initialize the database
        priceAggregateRepository.saveAndFlush(priceAggregate);

        // Get the priceAggregate
        restPriceAggregateMockMvc
            .perform(get(ENTITY_API_URL_ID, priceAggregate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(priceAggregate.getId().intValue()))
            .andExpect(jsonPath("$.symbolCrypto").value(DEFAULT_SYMBOL_CRYPTO))
            .andExpect(jsonPath("$.symbol").value(DEFAULT_SYMBOL))
            .andExpect(jsonPath("$.bidPrice").value(DEFAULT_BID_PRICE.doubleValue()))
            .andExpect(jsonPath("$.bidQty").value(DEFAULT_BID_QTY.doubleValue()))
            .andExpect(jsonPath("$.askPrice").value(DEFAULT_ASK_PRICE.doubleValue()))
            .andExpect(jsonPath("$.askQty").value(DEFAULT_ASK_QTY.doubleValue()))
            .andExpect(jsonPath("$.bid").value(DEFAULT_BID.doubleValue()))
            .andExpect(jsonPath("$.bidSize").value(DEFAULT_BID_SIZE.doubleValue()))
            .andExpect(jsonPath("$.ask").value(DEFAULT_ASK.doubleValue()))
            .andExpect(jsonPath("$.askSize").value(DEFAULT_ASK_SIZE.doubleValue()))
            .andExpect(jsonPath("$.sourceType").value(DEFAULT_SOURCE_TYPE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPriceAggregate() throws Exception {
        // Get the priceAggregate
        restPriceAggregateMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPriceAggregate() throws Exception {
        // Initialize the database
        priceAggregateRepository.saveAndFlush(priceAggregate);

        int databaseSizeBeforeUpdate = priceAggregateRepository.findAll().size();

        // Update the priceAggregate
        PriceAggregate updatedPriceAggregate = priceAggregateRepository.findById(priceAggregate.getId()).get();
        // Disconnect from session so that the updates on updatedPriceAggregate are not directly saved in db
        em.detach(updatedPriceAggregate);
        updatedPriceAggregate
            .symbolCrypto(UPDATED_SYMBOL_CRYPTO)
            .symbol(UPDATED_SYMBOL)
            .bidPrice(UPDATED_BID_PRICE)
            .bidQty(UPDATED_BID_QTY)
            .askPrice(UPDATED_ASK_PRICE)
            .askQty(UPDATED_ASK_QTY)
            .bid(UPDATED_BID)
            .bidSize(UPDATED_BID_SIZE)
            .ask(UPDATED_ASK)
            .askSize(UPDATED_ASK_SIZE)
            .sourceType(UPDATED_SOURCE_TYPE);
        PriceAggregateDTO priceAggregateDTO = priceAggregateMapper.toDto(updatedPriceAggregate);

        restPriceAggregateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, priceAggregateDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceAggregateDTO))
            )
            .andExpect(status().isOk());

        // Validate the PriceAggregate in the database
        List<PriceAggregate> priceAggregateList = priceAggregateRepository.findAll();
        assertThat(priceAggregateList).hasSize(databaseSizeBeforeUpdate);
        PriceAggregate testPriceAggregate = priceAggregateList.get(priceAggregateList.size() - 1);
        assertThat(testPriceAggregate.getSymbolCrypto()).isEqualTo(UPDATED_SYMBOL_CRYPTO);
        assertThat(testPriceAggregate.getSymbol()).isEqualTo(UPDATED_SYMBOL);
        assertThat(testPriceAggregate.getBidPrice()).isEqualTo(UPDATED_BID_PRICE);
        assertThat(testPriceAggregate.getBidQty()).isEqualTo(UPDATED_BID_QTY);
        assertThat(testPriceAggregate.getAskPrice()).isEqualTo(UPDATED_ASK_PRICE);
        assertThat(testPriceAggregate.getAskQty()).isEqualTo(UPDATED_ASK_QTY);
        assertThat(testPriceAggregate.getBid()).isEqualTo(UPDATED_BID);
        assertThat(testPriceAggregate.getBidSize()).isEqualTo(UPDATED_BID_SIZE);
        assertThat(testPriceAggregate.getAsk()).isEqualTo(UPDATED_ASK);
        assertThat(testPriceAggregate.getAskSize()).isEqualTo(UPDATED_ASK_SIZE);
        assertThat(testPriceAggregate.getSourceType()).isEqualTo(UPDATED_SOURCE_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingPriceAggregate() throws Exception {
        int databaseSizeBeforeUpdate = priceAggregateRepository.findAll().size();
        priceAggregate.setId(count.incrementAndGet());

        // Create the PriceAggregate
        PriceAggregateDTO priceAggregateDTO = priceAggregateMapper.toDto(priceAggregate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriceAggregateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, priceAggregateDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceAggregateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceAggregate in the database
        List<PriceAggregate> priceAggregateList = priceAggregateRepository.findAll();
        assertThat(priceAggregateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPriceAggregate() throws Exception {
        int databaseSizeBeforeUpdate = priceAggregateRepository.findAll().size();
        priceAggregate.setId(count.incrementAndGet());

        // Create the PriceAggregate
        PriceAggregateDTO priceAggregateDTO = priceAggregateMapper.toDto(priceAggregate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceAggregateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceAggregateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceAggregate in the database
        List<PriceAggregate> priceAggregateList = priceAggregateRepository.findAll();
        assertThat(priceAggregateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPriceAggregate() throws Exception {
        int databaseSizeBeforeUpdate = priceAggregateRepository.findAll().size();
        priceAggregate.setId(count.incrementAndGet());

        // Create the PriceAggregate
        PriceAggregateDTO priceAggregateDTO = priceAggregateMapper.toDto(priceAggregate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceAggregateMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(priceAggregateDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PriceAggregate in the database
        List<PriceAggregate> priceAggregateList = priceAggregateRepository.findAll();
        assertThat(priceAggregateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePriceAggregateWithPatch() throws Exception {
        // Initialize the database
        priceAggregateRepository.saveAndFlush(priceAggregate);

        int databaseSizeBeforeUpdate = priceAggregateRepository.findAll().size();

        // Update the priceAggregate using partial update
        PriceAggregate partialUpdatedPriceAggregate = new PriceAggregate();
        partialUpdatedPriceAggregate.setId(priceAggregate.getId());

        partialUpdatedPriceAggregate
            .symbolCrypto(UPDATED_SYMBOL_CRYPTO)
            .symbol(UPDATED_SYMBOL)
            .askPrice(UPDATED_ASK_PRICE)
            .askQty(UPDATED_ASK_QTY)
            .bidSize(UPDATED_BID_SIZE)
            .sourceType(UPDATED_SOURCE_TYPE);

        restPriceAggregateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPriceAggregate.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPriceAggregate))
            )
            .andExpect(status().isOk());

        // Validate the PriceAggregate in the database
        List<PriceAggregate> priceAggregateList = priceAggregateRepository.findAll();
        assertThat(priceAggregateList).hasSize(databaseSizeBeforeUpdate);
        PriceAggregate testPriceAggregate = priceAggregateList.get(priceAggregateList.size() - 1);
        assertThat(testPriceAggregate.getSymbolCrypto()).isEqualTo(UPDATED_SYMBOL_CRYPTO);
        assertThat(testPriceAggregate.getSymbol()).isEqualTo(UPDATED_SYMBOL);
        assertThat(testPriceAggregate.getBidPrice()).isEqualTo(DEFAULT_BID_PRICE);
        assertThat(testPriceAggregate.getBidQty()).isEqualTo(DEFAULT_BID_QTY);
        assertThat(testPriceAggregate.getAskPrice()).isEqualTo(UPDATED_ASK_PRICE);
        assertThat(testPriceAggregate.getAskQty()).isEqualTo(UPDATED_ASK_QTY);
        assertThat(testPriceAggregate.getBid()).isEqualTo(DEFAULT_BID);
        assertThat(testPriceAggregate.getBidSize()).isEqualTo(UPDATED_BID_SIZE);
        assertThat(testPriceAggregate.getAsk()).isEqualTo(DEFAULT_ASK);
        assertThat(testPriceAggregate.getAskSize()).isEqualTo(DEFAULT_ASK_SIZE);
        assertThat(testPriceAggregate.getSourceType()).isEqualTo(UPDATED_SOURCE_TYPE);
    }

    @Test
    @Transactional
    void fullUpdatePriceAggregateWithPatch() throws Exception {
        // Initialize the database
        priceAggregateRepository.saveAndFlush(priceAggregate);

        int databaseSizeBeforeUpdate = priceAggregateRepository.findAll().size();

        // Update the priceAggregate using partial update
        PriceAggregate partialUpdatedPriceAggregate = new PriceAggregate();
        partialUpdatedPriceAggregate.setId(priceAggregate.getId());

        partialUpdatedPriceAggregate
            .symbolCrypto(UPDATED_SYMBOL_CRYPTO)
            .symbol(UPDATED_SYMBOL)
            .bidPrice(UPDATED_BID_PRICE)
            .bidQty(UPDATED_BID_QTY)
            .askPrice(UPDATED_ASK_PRICE)
            .askQty(UPDATED_ASK_QTY)
            .bid(UPDATED_BID)
            .bidSize(UPDATED_BID_SIZE)
            .ask(UPDATED_ASK)
            .askSize(UPDATED_ASK_SIZE)
            .sourceType(UPDATED_SOURCE_TYPE);

        restPriceAggregateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPriceAggregate.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPriceAggregate))
            )
            .andExpect(status().isOk());

        // Validate the PriceAggregate in the database
        List<PriceAggregate> priceAggregateList = priceAggregateRepository.findAll();
        assertThat(priceAggregateList).hasSize(databaseSizeBeforeUpdate);
        PriceAggregate testPriceAggregate = priceAggregateList.get(priceAggregateList.size() - 1);
        assertThat(testPriceAggregate.getSymbolCrypto()).isEqualTo(UPDATED_SYMBOL_CRYPTO);
        assertThat(testPriceAggregate.getSymbol()).isEqualTo(UPDATED_SYMBOL);
        assertThat(testPriceAggregate.getBidPrice()).isEqualTo(UPDATED_BID_PRICE);
        assertThat(testPriceAggregate.getBidQty()).isEqualTo(UPDATED_BID_QTY);
        assertThat(testPriceAggregate.getAskPrice()).isEqualTo(UPDATED_ASK_PRICE);
        assertThat(testPriceAggregate.getAskQty()).isEqualTo(UPDATED_ASK_QTY);
        assertThat(testPriceAggregate.getBid()).isEqualTo(UPDATED_BID);
        assertThat(testPriceAggregate.getBidSize()).isEqualTo(UPDATED_BID_SIZE);
        assertThat(testPriceAggregate.getAsk()).isEqualTo(UPDATED_ASK);
        assertThat(testPriceAggregate.getAskSize()).isEqualTo(UPDATED_ASK_SIZE);
        assertThat(testPriceAggregate.getSourceType()).isEqualTo(UPDATED_SOURCE_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingPriceAggregate() throws Exception {
        int databaseSizeBeforeUpdate = priceAggregateRepository.findAll().size();
        priceAggregate.setId(count.incrementAndGet());

        // Create the PriceAggregate
        PriceAggregateDTO priceAggregateDTO = priceAggregateMapper.toDto(priceAggregate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriceAggregateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, priceAggregateDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceAggregateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceAggregate in the database
        List<PriceAggregate> priceAggregateList = priceAggregateRepository.findAll();
        assertThat(priceAggregateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPriceAggregate() throws Exception {
        int databaseSizeBeforeUpdate = priceAggregateRepository.findAll().size();
        priceAggregate.setId(count.incrementAndGet());

        // Create the PriceAggregate
        PriceAggregateDTO priceAggregateDTO = priceAggregateMapper.toDto(priceAggregate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceAggregateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceAggregateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceAggregate in the database
        List<PriceAggregate> priceAggregateList = priceAggregateRepository.findAll();
        assertThat(priceAggregateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPriceAggregate() throws Exception {
        int databaseSizeBeforeUpdate = priceAggregateRepository.findAll().size();
        priceAggregate.setId(count.incrementAndGet());

        // Create the PriceAggregate
        PriceAggregateDTO priceAggregateDTO = priceAggregateMapper.toDto(priceAggregate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceAggregateMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceAggregateDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PriceAggregate in the database
        List<PriceAggregate> priceAggregateList = priceAggregateRepository.findAll();
        assertThat(priceAggregateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePriceAggregate() throws Exception {
        // Initialize the database
        priceAggregateRepository.saveAndFlush(priceAggregate);

        int databaseSizeBeforeDelete = priceAggregateRepository.findAll().size();

        // Delete the priceAggregate
        restPriceAggregateMockMvc
            .perform(delete(ENTITY_API_URL_ID, priceAggregate.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PriceAggregate> priceAggregateList = priceAggregateRepository.findAll();
        assertThat(priceAggregateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
