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

    private String symbol;

    private Double bidPrice;

    private Double bidQty;

    private Double askPrice;

    private Double askQty;

    private Double bid;

    private Double bidSize;

    private Double ask;

    private Double askSize;

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getBidSize() {
        return bidSize;
    }

    public void setBidSize(Double bidSize) {
        this.bidSize = bidSize;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getAskSize() {
        return askSize;
    }

    public void setAskSize(Double askSize) {
        this.askSize = askSize;
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
            ", symbol='" + getSymbol() + "'" +
            ", bidPrice=" + getBidPrice() +
            ", bidQty=" + getBidQty() +
            ", askPrice=" + getAskPrice() +
            ", askQty=" + getAskQty() +
            ", bid=" + getBid() +
            ", bidSize=" + getBidSize() +
            ", ask=" + getAsk() +
            ", askSize=" + getAskSize() +
            ", sourceType='" + getSourceType() + "'" +
            "}";
    }
}
