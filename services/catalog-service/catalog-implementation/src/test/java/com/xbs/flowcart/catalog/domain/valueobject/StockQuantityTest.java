package com.xbs.flowcart.catalog.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StockQuantityTest {

    @Test
    void shouldThrowExceptionForNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> new StockQuantity(-1));
    }

    @Test
    void shouldAddStockCorrectly() {
        StockQuantity stock = new StockQuantity(10);
        StockQuantity newStock = stock.add(5);
        assertThat(newStock.amount()).isEqualTo(15);
    }

    @Test
    void shouldThrowExceptionWhenAddingNegativeAmount() {
        StockQuantity stock = new StockQuantity(10);
        assertThrows(IllegalArgumentException.class, () -> stock.add(-5));
    }

    @Test
    void shouldSubtractStockCorrectly() {
        StockQuantity stock = new StockQuantity(10);
        StockQuantity newStock = stock.subtract(3);
        assertThat(newStock.amount()).isEqualTo(7);
    }

    @Test
    void shouldThrowExceptionWhenSubtractingNegativeAmount() {
        StockQuantity stock = new StockQuantity(10);
        assertThrows(IllegalArgumentException.class, () -> stock.subtract(-3));
    }

    @Test
    void shouldThrowExceptionWhenSubtractingMoreThanAvailable() {
        StockQuantity stock = new StockQuantity(5);
        assertThrows(IllegalStateException.class, () -> stock.subtract(6));
    }
}