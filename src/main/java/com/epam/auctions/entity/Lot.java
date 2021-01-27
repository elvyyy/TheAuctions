package com.epam.auctions.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Lot extends Entity<Integer> {
    private LocalDateTime createdAt;
    private Integer createdBy;
    private Integer verifiedBy;
    private BigDecimal minimalBid;
    private String description;
    private Integer soldTo;
    private LotStatus lotStatus;
    private String photoPath;
    private Integer durationInMinutes;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(Integer verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public BigDecimal getMinimalBid() {
        return minimalBid;
    }

    public void setMinimalBid(BigDecimal minimalBid) {
        this.minimalBid = minimalBid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSoldTo() {
        return soldTo;
    }

    public void setSoldTo(Integer soldTo) {
        this.soldTo = soldTo;
    }

    public LotStatus getLotStatus() {
        return lotStatus;
    }

    public void setLotStatus(LotStatus lotStatus) {
        this.lotStatus = lotStatus;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lot{");
        sb.append("id=").append(id);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", verifiedBy=").append(verifiedBy);
        sb.append(", minimalBid=").append(minimalBid);
        sb.append(", description='").append(description).append('\'');
        sb.append(", soldTo=").append(soldTo);
        sb.append(", lotStatus=").append(lotStatus);
        sb.append(", photoPath='").append(photoPath).append('\'');
        sb.append(", durationInMinutes=").append(durationInMinutes);
        sb.append(", startAt=").append(startAt);
        sb.append(", endAt=").append(endAt);
        sb.append('}');
        return sb.toString();
    }

    public Lot() {
    }
}
