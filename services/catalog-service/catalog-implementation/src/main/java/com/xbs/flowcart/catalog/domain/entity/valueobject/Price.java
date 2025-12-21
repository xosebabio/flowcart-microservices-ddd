package com.xbs.flowcart.catalog.domain.entity.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public record Price(
        Currency currency,
        BigDecimal amount
) {
    public Price {
        Objects.requireNonNull(amount, "The amount cannot be null");
        Objects.requireNonNull(currency, "The currency cannot be null");

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("The amount cannot be negative");
        }

        amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public boolean isGreaterThan(Price other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot compare prices in different currencies");
        }
        return this.amount.compareTo(other.amount) > 0;
    }
}