package com.xbs.flowcart.catalog.application.assembler;

import com.xbs.flowcart.catalog.application.dto.ProductDto;
import com.xbs.flowcart.catalog.domain.entity.Product;
import com.xbs.flowcart.catalog.domain.entity.valueobject.Category;
import com.xbs.flowcart.catalog.domain.entity.valueobject.Description;
import com.xbs.flowcart.catalog.domain.entity.valueobject.Price;
import com.xbs.flowcart.catalog.domain.entity.valueobject.StockQuantity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductAssemblerTest {

    @Test
    void shouldMapProductToDto() {
        UUID id = UUID.randomUUID();
        Product product = Product.builder()
                .id(id)
                .name("Test Product")
                .description(new Description("Short description", "Long description"))
                .price(new Price(Currency.getInstance("USD"), BigDecimal.valueOf(99.99)))
                .stockQuantity(new StockQuantity(10))
                .category(Category.LAPTOP)
                .isActive(true)
                .build();

        ProductDto result = ProductAssembler.toDto(product);

        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals("Test Product", result.name());
        assertEquals("Short description", result.shortDescription());
        assertEquals("Long description", result.longDescription());
        assertEquals(BigDecimal.valueOf(99.99), result.price());
        assertEquals("USD", result.currency());
        assertEquals(10, result.stock());
        assertEquals("LAPTOP", result.category());
        assertTrue(result.active());
    }
}