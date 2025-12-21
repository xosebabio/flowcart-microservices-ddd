package com.xbs.flowcart.catalog.domain.entity.valueobject;

public record Description(String shortDescription, String longDescription) {
    public Description {
        if (shortDescription == null || shortDescription.isBlank()) {
            throw new IllegalArgumentException("Short description cannot be null or blank.");
        }

        if (shortDescription.length() > 255) {
            throw new IllegalArgumentException("Short description cannot exceed 255 characters.");
        }

        shortDescription = shortDescription.trim();
        if (longDescription != null) {
            longDescription = longDescription.trim();
        }
    }
}