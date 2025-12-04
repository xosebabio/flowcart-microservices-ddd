package com.xbs.flowcart.catalog.model;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String shortDescription,
        String longDescription,
        BigDecimal price,
        String currency,
        Integer stock,
        String category,
        boolean active
) {
}