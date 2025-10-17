package com.xbs.flowcart.catalog.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {

    @Test
    void shouldReturnCorrectDisplayName() {
        assertThat(Category.LAPTOP.getDisplayName()).isEqualTo("Laptop");
        assertThat(Category.KEYBOARD.getDisplayName()).isEqualTo("Keyboard");
    }
}