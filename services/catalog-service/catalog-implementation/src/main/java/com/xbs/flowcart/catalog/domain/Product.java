package com.xbs.flowcart.catalog.domain;

import com.xbs.flowcart.catalog.domain.valueobject.Category;
import com.xbs.flowcart.catalog.domain.valueobject.Description;
import com.xbs.flowcart.catalog.domain.valueobject.Price;
import com.xbs.flowcart.catalog.domain.valueobject.StockQuantity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class Product {

    private final UUID id;
    private String name;
    private Description description;
    private Price price;
    private StockQuantity stockQuantity;
    private Category category;
    private boolean isActive;

    public void changeName(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        this.name = newName;
    }

    public void changePrice(Price newPrice) {
        checkActive();
        if (!this.price.currency().equals(newPrice.currency())) {
            throw new IllegalArgumentException("The new price must be in the same currency");
        }
        this.price = newPrice;
    }

    public void addStock(int quantityToAdd) {
        this.stockQuantity = this.stockQuantity.add(quantityToAdd);
    }

    public void removeStock(int quantityToRemove) {
        checkActive();
        this.stockQuantity = this.stockQuantity.subtract(quantityToRemove);
    }

    public void updateDescription(Description newDescription) {
        this.description = newDescription;
    }

    public void changeCategory(Category newCategory) {
        this.category = newCategory;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }

    private void checkActive() {
        if (!this.isActive) {
            throw new IllegalStateException("Cannot modify an inactive product");
        }
    }
}