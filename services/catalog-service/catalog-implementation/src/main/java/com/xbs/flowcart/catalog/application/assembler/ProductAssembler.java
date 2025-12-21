package com.xbs.flowcart.catalog.application.assembler;

import com.xbs.flowcart.catalog.application.dto.ProductDto;
import com.xbs.flowcart.catalog.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductAssembler {

    private ProductAssembler(){}

    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription().shortDescription(),
                product.getDescription().longDescription(),
                product.getPrice().amount(),
                product.getPrice().currency().getCurrencyCode(),
                product.getStockQuantity().amount(),
                product.getCategory().name(),
                product.isActive()
        );
    }
}