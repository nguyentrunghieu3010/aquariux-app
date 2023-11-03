package com.aquariux.crypto.domain;

import com.aquariux.crypto.domain.enumeration.SourceType;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PriceAggregate.
 */
@Entity
@Table(name = "price_aggregate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PriceAggregate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "symbol_crypto")
    private String symbolCrypto;

    @Column(name = "bid_price")
    private Double bidPrice;

    @Column(name = "bid_qty")
    private Double bidQty;

    @Column(name = "ask_price")
    private Double askPrice;

    @Column(name = "ask_qty")
    private Double askQty;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_type")
    private SourceType sourceType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PriceAggregate id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbolCrypto() {
        return this.symbolCrypto;
    }

    public PriceAggregate symbolCrypto(String symbolCrypto) {
        this.setSymbolCrypto(symbolCrypto);
        return this;
    }

    public void setSymbolCrypto(String symbolCrypto) {
        this.symbolCrypto = symbolCrypto;
    }

    public Double getBidPrice() {
        return this.bidPrice;
    }

    public PriceAggregate bidPrice(Double bidPrice) {
        this.setBidPrice(bidPrice);
        return this;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Double getBidQty() {
        return this.bidQty;
    }

    public PriceAggregate bidQty(Double bidQty) {
        this.setBidQty(bidQty);
        return this;
    }

    public void setBidQty(Double bidQty) {
        this.bidQty = bidQty;
    }

    public Double getAskPrice() {
        return this.askPrice;
    }

    public PriceAggregate askPrice(Double askPrice) {
        this.setAskPrice(askPrice);
        return this;
    }

    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }

    public Double getAskQty() {
        return this.askQty;
    }

    public PriceAggregate askQty(Double askQty) {
        this.setAskQty(askQty);
        return this;
    }

    public void setAskQty(Double askQty) {
        this.askQty = askQty;
    }

    public SourceType getSourceType() {
        return this.sourceType;
    }

    public PriceAggregate sourceType(SourceType sourceType) {
        this.setSourceType(sourceType);
        return this;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PriceAggregate)) {
            return false;
        }
        return id != null && id.equals(((PriceAggregate) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PriceAggregate{" +
            "id=" + getId() +
            ", symbolCrypto='" + getSymbolCrypto() + "'" +
            ", bidPrice=" + getBidPrice() +
            ", bidQty=" + getBidQty() +
            ", askPrice=" + getAskPrice() +
            ", askQty=" + getAskQty() +
            ", sourceType='" + getSourceType() + "'" +
            "}";
    }
}
