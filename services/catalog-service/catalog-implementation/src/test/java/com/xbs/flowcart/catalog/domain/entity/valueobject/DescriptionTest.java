package com.xbs.flowcart.catalog.domain.entity.valueobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DescriptionTest {

    @Test
    void shouldCreateDescriptionSuccessfully() {
        Description description = new Description("  Short  ", "  Long  ");
        assertThat(description.shortDescription()).isEqualTo("Short");
        assertThat(description.longDescription()).isEqualTo("Long");
    }

    @Test
    void shouldCreateWithNullLongDescription() {
        Description description = new Description("Short", null);
        assertThat(description.shortDescription()).isEqualTo("Short");
        assertThat(description.longDescription()).isNull();
    }

    @Test
    void shouldThrowExceptionForNullShortDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Description(null, "Long"));
    }

    @Test
    void shouldThrowExceptionForBlankShortDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Description(" ", "Long"));
    }

    @Test
    void shouldThrowExceptionForShortDescriptionExceedingMaxLength() {
        String longString = "a".repeat(256);
        assertThrows(IllegalArgumentException.class, () -> new Description(longString, "Long"));
    }
}