package com.xbs.flowcart.catalog.domain.repository;

import com.xbs.flowcart.catalog.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    void save(Product product);

    Optional<Product> findById(UUID productId);

    List<Product> findAll();

    void modify(Product product);

    void deleteById(UUID productId);

}
