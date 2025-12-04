package com.xbs.flowcart.catalog.infrastructure.api;

import com.xbs.flowcart.catalog.CatalogApi;
import com.xbs.flowcart.catalog.model.CreateProductRequest;
import com.xbs.flowcart.catalog.model.ProductResponse;

import java.util.UUID;

public class CatalogController implements CatalogApi {
    @Override
    public void createProduct(CreateProductRequest request) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ProductResponse getProduct(UUID id) {
        return null;
    }
}
