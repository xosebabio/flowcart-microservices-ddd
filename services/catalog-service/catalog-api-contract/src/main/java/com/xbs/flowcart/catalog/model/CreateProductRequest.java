package com.xbs.flowcart.catalog.model;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "Name cannot be empty")
        String name,

        @NotBlank(message = "Short description cannot be empty")
        @Size(max = 255, message = "Short description cannot exceed 255 characters")
        String shortDescription,

        String longDescription,

        @NotNull(message = "Price cannot be null")
        @Positive(message = "Price must be greater than zero")
        BigDecimal price,

        @NotNull(message = "Currency cannot be null")
        @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a 3-letter ISO code")
        String currency,

        @NotNull(message = "Stock cannot be null")
        @PositiveOrZero(message = "Stock cannot be negative")
        Integer stock,

        @NotBlank(message = "Category cannot be empty")
        String category
) {
}
