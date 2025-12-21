package com.xbs.flowcart.catalog.domain.entity.valueobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceTest {

    private final Currency usd = Currency.getInstance("USD");
    private final Currency eur = Currency.getInstance("EUR");

    @Test
    void shouldCreatePriceAndScaleAmount() {
        Price price = new Price(usd, new BigDecimal("99.987"));
        assertThat(price.currency()).isEqualTo(usd);
        assertThat(price.amount()).isEqualByComparingTo(new BigDecimal("99.99"));
    }

    @Test
    void shouldThrowExceptionForNullAmount() {
        assertThrows(NullPointerException.class, () -> new Price(usd, null));
    }

    @Test
    void shouldThrowExceptionForNullCurrency() {
        assertThrows(NullPointerException.class, () -> new Price(null, BigDecimal.TEN));
    }

    @Test
    void shouldThrowExceptionForNegativeAmount() {
        BigDecimal negativeAmount = new BigDecimal("-1.00");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Price(usd, negativeAmount));
        assertEquals("The amount cannot be negative", exception.getMessage());
    }

    @Test
    void shouldReturnTrueWhenPriceIsGreaterThanOther() {
        Price priceA = new Price(usd, BigDecimal.TEN);
        Price priceB = new Price(usd, BigDecimal.ONE);
        assertThat(priceA.isGreaterThan(priceB)).isTrue();
    }

    @Test
    void shouldReturnFalseWhenPriceIsNotGreaterThanOther() {
        Price priceA = new Price(usd, BigDecimal.ONE);
        Price priceB = new Price(usd, BigDecimal.TEN);
        assertThat(priceA.isGreaterThan(priceB)).isFalse();
    }

    @Test
    void shouldThrowExceptionWhenComparingDifferentCurrencies() {
        Price priceA = new Price(usd, BigDecimal.TEN);
        Price priceB = new Price(eur, BigDecimal.ONE);
        assertThrows(IllegalArgumentException.class, () -> priceA.isGreaterThan(priceB));
    }
}