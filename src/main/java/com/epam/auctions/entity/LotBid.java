package com.epam.auctions.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class LotBid {
    private LocalDateTime createdAt;
    private String createdBy;
    private BigDecimal currentBid;
    private int lotId;

    public LotBid() {
        createdAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public BigDecimal getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(BigDecimal currentBid) {
        this.currentBid = currentBid;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotId, currentBid, createdBy, createdAt);
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
}
