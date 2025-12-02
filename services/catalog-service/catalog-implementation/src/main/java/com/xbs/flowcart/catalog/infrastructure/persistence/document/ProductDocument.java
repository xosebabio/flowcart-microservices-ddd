package com.xbs.flowcart.catalog.infrastructure.persistence.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@Document(collection = "products")
public class ProductDocument {

    @Id
    private UUID id;

    private String name;
    private String category;
    private boolean isActive;
    private Integer stockQuantity;

    private DescriptionData description;
    private PriceData price;

    @Data
    @Builder
    public static class DescriptionData {
        private String shortDescription;
        private String longDescription;
    }

    @Data
    @Builder
    public static class PriceData {
        private BigDecimal amount;
        private String currency;
    }
}