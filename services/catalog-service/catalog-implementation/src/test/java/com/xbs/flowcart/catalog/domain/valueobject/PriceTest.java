package com.xbs.flowcart.catalog.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceTest {

    private final Currency USD = Currency.getInstance("USD");
    private final Currency EUR = Currency.getInstance("EUR");

    @Test
    void shouldCreatePriceAndScaleAmount() {
        Price price = new Price(USD, new BigDecimal("99.987"));
        assertThat(price.currency()).isEqualTo(USD);
        assertThat(price.amount()).isEqualByComparingTo(new BigDecimal("99.99"));
    }

    @Test
    void shouldThrowExceptionForNullAmount() {
        assertThrows(NullPointerException.class, () -> new Price(USD, null));
    }

    @Test
    void shouldThrowExceptionForNullCurrency() {
        assertThrows(NullPointerException.class, () -> new Price(null, BigDecimal.TEN));
    }

    @Test
    void shouldThrowExceptionForNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> new Price(USD, new BigDecimal("-1.00")));
    }

    @Test
    void shouldReturnTrueWhenPriceIsGreaterThanOther() {
        Price priceA = new Price(USD, BigDecimal.TEN);
        Price priceB = new Price(USD, BigDecimal.ONE);
        assertThat(priceA.isGreaterThan(priceB)).isTrue();
    }

    @Test
    void shouldReturnFalseWhenPriceIsNotGreaterThanOther() {
        Price priceA = new Price(USD, BigDecimal.ONE);
        Price priceB = new Price(USD, BigDecimal.TEN);
        assertThat(priceA.isGreaterThan(priceB)).isFalse();
    }

    @Test
    void shouldThrowExceptionWhenComparingDifferentCurrencies() {
        Price priceA = new Price(USD, BigDecimal.TEN);
        Price priceB = new Price(EUR, BigDecimal.ONE);
        assertThrows(IllegalArgumentException.class, () -> priceA.isGreaterThan(priceB));
    }
}