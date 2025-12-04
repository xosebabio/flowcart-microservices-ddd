package com.xbs.flowcart.catalog;

import com.xbs.flowcart.catalog.model.CreateProductRequest;
import com.xbs.flowcart.catalog.model.ProductResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.UUID;


@HttpExchange("/api/v1/products")
public interface CatalogApi {

    @PostExchange
    void createProduct(@RequestBody CreateProductRequest request);

    @GetExchange("/{id}")
    ProductResponse getProduct(@PathVariable("id") UUID id);
}