package com.xbs.flowcart.catalog.domain;

import com.xbs.flowcart.catalog.domain.entity.Product;
import com.xbs.flowcart.catalog.domain.entity.valueobject.Category;
import com.xbs.flowcart.catalog.domain.entity.valueobject.Description;
import com.xbs.flowcart.catalog.domain.entity.valueobject.Price;
import com.xbs.flowcart.catalog.domain.entity.valueobject.StockQuantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {

    private Product baseProduct;

    @BeforeEach
    void setUp() {
        baseProduct = Product.builder()
                .id(UUID.randomUUID())
                .name("Base Product")
                .description(new Description("Short desc", "Long desc"))
                .price(new Price(Currency.getInstance("USD"), new BigDecimal("100.00")))
                .stockQuantity(new StockQuantity(20))
                .category(Category.LAPTOP)
                .isActive(true)
                .build();
    }

    @Test
    void shouldChangeName() {
        String newName = "Updated Product Name";
        baseProduct.changeName(newName);
        assertThat(baseProduct.getName()).isEqualTo(newName);
    }

    @Test
    void shouldThrowExceptionForBlankName() {
        assertThrows(IllegalArgumentException.class, () -> baseProduct.changeName(""));
        assertThrows(IllegalArgumentException.class, () -> baseProduct.changeName(null));
    }

    @Test
    void shouldChangePrice() {
        Price newPrice = new Price(Currency.getInstance("USD"), new BigDecimal("99.99"));
        baseProduct.changePrice(newPrice);
        assertThat(baseProduct.getPrice()).isEqualTo(newPrice);
    }

    @Test
    void shouldThrowExceptionWhenChangingPriceWithDifferentCurrency() {
        Price newPriceInEur = new Price(Currency.getInstance("EUR"), new BigDecimal("99.99"));
        assertThrows(IllegalArgumentException.class, () -> baseProduct.changePrice(newPriceInEur));
    }

    @Test
    void shouldThrowExceptionWhenChangingPriceOnInactiveProduct() {
        Product inactiveProduct = baseProduct.toBuilder().isActive(false).build();
        Price newPrice = new Price(Currency.getInstance("USD"), new BigDecimal("99.99"));
        assertThrows(IllegalStateException.class, () -> inactiveProduct.changePrice(newPrice));
    }

    @Test
    void shouldAddStock() {
        baseProduct.addStock(10);
        assertThat(baseProduct.getStockQuantity().amount()).isEqualTo(30);
    }

    @Test
    void shouldRemoveStockWhenProductIsActive() {
        baseProduct.removeStock(5);
        assertThat(baseProduct.getStockQuantity().amount()).isEqualTo(15);
    }

    @Test
    void shouldThrowExceptionWhenRemovingStockFromInactiveProduct() {
        Product inactiveProduct = baseProduct.toBuilder().isActive(false).build();
        assertThrows(IllegalStateException.class, () -> inactiveProduct.removeStock(5));
    }

    @Test
    void shouldUpdateDescription() {
        Description newDescription = new Description("New short desc", "New long desc");
        baseProduct.updateDescription(newDescription);
        assertThat(baseProduct.getDescription()).isEqualTo(newDescription);
    }

    @Test
    void shouldChangeCategory() {
        baseProduct.changeCategory(Category.KEYBOARD);
        assertThat(baseProduct.getCategory()).isEqualTo(Category.KEYBOARD);
    }

    @Test
    void shouldDeactivateProduct() {
        baseProduct.deactivate();
        assertThat(baseProduct.isActive()).isFalse();
    }

    @Test
    void shouldActivateProduct() {
        Product inactiveProduct = baseProduct.toBuilder().isActive(false).build();
        inactiveProduct.activate();
        assertThat(inactiveProduct.isActive()).isTrue();
    }
}