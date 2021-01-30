package com.epam.auctions.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class LotBid {
    private int lotId;
    private BigDecimal currentBid;
    private String createdBy;
    private LocalDateTime createdAt;

    public LotBid() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LotBid)) return false;
        LotBid lotBid = (LotBid) o;
        return lotId == lotBid.lotId && Objects.equals(currentBid, lotBid.currentBid) && Objects.equals(createdBy, lotBid.createdBy) && Objects.equals(createdAt, lotBid.createdAt);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LotBid{");
        sb.append("lotId=").append(lotId);
        sb.append(", currentBid=").append(currentBid);
        sb.append(", createdBy='").append(createdBy).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotId, currentBid, createdBy, createdAt);
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public BigDecimal getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(BigDecimal currentBid) {
        this.currentBid = currentBid;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LotBid(int lotId, BigDecimal currentBid, String createdBy) {
        this(lotId, currentBid, createdBy, LocalDateTime.now());
    }

    public LotBid(int lotId, BigDecimal currentBid, String createdBy, LocalDateTime createdAt) {
        this.lotId = lotId;
        this.currentBid = currentBid;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
}
