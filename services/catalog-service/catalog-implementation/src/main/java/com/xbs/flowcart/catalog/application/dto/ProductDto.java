package com.xbs.flowcart.catalog.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto(
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