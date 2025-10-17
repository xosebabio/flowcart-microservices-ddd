package com.xbs.flowcart.catalog.domain.valueobject;

public record StockQuantity(int amount) {

    public StockQuantity {
        if (amount < 0) {
            throw new IllegalArgumentException("The amount can't be negative");
        }
    }

    public StockQuantity subtract(int amountToSubtract) {
        if (amountToSubtract < 0) {
            throw new IllegalArgumentException("The amount to subtract can't be negative");
        }
        if (this.amount < amountToSubtract) {
            throw new IllegalStateException(
                    "Not enough stock to subtract. Current stock: " + this.amount + ", tried to subtract: " + amountToSubtract
            );
        }
        return new StockQuantity(this.amount - amountToSubtract);
    }

    public StockQuantity add(int amountToAdd) {
        if (amountToAdd < 0) {
            throw new IllegalArgumentException("The amount to add can't be negative");
        }
        return new StockQuantity(this.amount + amountToAdd);
    }
}
