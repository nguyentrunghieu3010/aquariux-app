package com.aquariux.crypto.service.dto;

import com.aquariux.crypto.domain.enumeration.SourceType;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.aquariux.crypto.domain.PriceAggregate} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PriceAggregateDTO implements Serializable {

    private Long id;

    private String symbolCrypto;

    private Double bidPrice;

    private Double bidQty;

    private Double askPrice;

    private Double askQty;

    private SourceType sourceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbolCrypto() {
        return symbolCrypto;
    }

    public void setSymbolCrypto(String symbolCrypto) {
        this.symbolCrypto = symbolCrypto;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Double getBidQty() {
        return bidQty;
    }

    public void setBidQty(Double bidQty) {
        this.bidQty = bidQty;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }

    public Double getAskQty() {
        return askQty;
    }

    public void setAskQty(Double askQty) {
        this.askQty = askQty;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PriceAggregateDTO)) {
            return false;
        }

        PriceAggregateDTO priceAggregateDTO = (PriceAggregateDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, priceAggregateDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PriceAggregateDTO{" +
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
