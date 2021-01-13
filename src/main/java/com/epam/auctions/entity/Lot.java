package com.epam.auctions.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Lot extends Entity<Integer> {
    private LocalDateTime createdAt;
    private String createdBy;
    private String verifiedBy;
    private BigDecimal minimalBid;
    private String description;
    private String soldTo;
    private LotStatus lotStatus;
}
