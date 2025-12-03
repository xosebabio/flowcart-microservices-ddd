package com.xbs.flowcart.catalog.infrastructure.persistence.mapper;

import com.xbs.flowcart.catalog.domain.Product;
import com.xbs.flowcart.catalog.domain.valueobject.Category;
import com.xbs.flowcart.catalog.domain.valueobject.Description;
import com.xbs.flowcart.catalog.domain.valueobject.Price;
import com.xbs.flowcart.catalog.domain.valueobject.StockQuantity;
import com.xbs.flowcart.catalog.infrastructure.persistence.document.ProductDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ProductPersistenceMapperTest {

    private final ProductPersistenceMapper mapper = new ProductPersistenceMapper();

    private Product domainProduct;
    private ProductDocument productDocument;

    @BeforeEach
    void setUp() {
        UUID id = UUID.randomUUID();

        domainProduct = Product.builder()
                .id(id)
                .name("Gaming Laptop")
                .description(new Description("Short desc", "Long description content"))
                .price(new Price(Currency.getInstance("USD"), new BigDecimal("1500.00")))
                .stockQuantity(new StockQuantity(10))
                .category(Category.LAPTOP)
                .isActive(true)
                .build();

        productDocument = ProductDocument.builder()
                .id(id)
                .name("Gaming Laptop")
                .category("LAPTOP")
                .stockQuantity(10)
                .isActive(true)
                .description(ProductDocument.DescriptionData.builder()
                        .shortDescription("Short desc")
                        .longDescription("Long description content")
                        .build())
                .price(ProductDocument.PriceData.builder()
                        .amount(new BigDecimal("1500.00"))
                        .currency("USD")
                        .build())
                .build();
    }

    @Test
    void shouldMapDomainToDocument() {
        ProductDocument result = mapper.toDocument(domainProduct);
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(productDocument);
    }

    @Test
    void shouldMapDocumentToDomain() {
        Product result = mapper.toDomain(productDocument);
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(domainProduct);
    }
}