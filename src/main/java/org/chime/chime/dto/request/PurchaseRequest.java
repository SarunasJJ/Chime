package org.chime.chime.dto.request;

import org.chime.chime.entities.enums.PurchaseStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseRequest(
        UUID listingId,
        UUID sellerId,
        UUID buyerId,
        BigDecimal purchasePrice,
        PurchaseStatus status
) {
}
