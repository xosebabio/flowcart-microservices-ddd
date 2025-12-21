package com.xbs.flowcart.catalog.infrastructure.persistence.mapper;

import com.xbs.flowcart.catalog.domain.entity.Product;
import com.xbs.flowcart.catalog.domain.entity.valueobject.Category;
import com.xbs.flowcart.catalog.domain.entity.valueobject.Description;
import com.xbs.flowcart.catalog.domain.entity.valueobject.Price;
import com.xbs.flowcart.catalog.domain.entity.valueobject.StockQuantity;
import com.xbs.flowcart.catalog.infrastructure.persistence.document.ProductDocument;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class ProductPersistenceMapper {

    public Product toDomain(ProductDocument productDocument) {
        return Product.builder()
                .id(productDocument.getId())
                .name(productDocument.getName())
                .description(new Description(
                        productDocument.getDescription().getShortDescription(),
                        productDocument.getDescription().getLongDescription()))
                .price(new Price(
                        Currency.getInstance(productDocument.getPrice().getCurrency()),
                        productDocument.getPrice().getAmount()
                ))
                .isActive(productDocument.isActive())
                .stockQuantity(new StockQuantity(productDocument.getStockQuantity()))
                .category(Category.valueOf(productDocument.getCategory()))
                .build();
    }

    public ProductDocument toDocument(Product product) {
        return ProductDocument.builder()
                .id(product.getId())
                .name(product.getName())
                .description(ProductDocument.DescriptionData.builder()
                        .shortDescription(product.getDescription().shortDescription())
                        .longDescription(product.getDescription().longDescription())
                        .build())
                .price(ProductDocument.PriceData.builder()
                        .amount(product.getPrice().amount())
                        .currency(String.valueOf(product.getPrice().currency()))
                        .build())
                .isActive(product.isActive())
                .stockQuantity(product.getStockQuantity().amount())
                .category(String.valueOf(product.getCategory()))
                .build();
    }
}
