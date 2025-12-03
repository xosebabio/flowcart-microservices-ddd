package com.xbs.flowcart.catalog.infrastructure.persistence.adapter;

import com.xbs.flowcart.catalog.domain.Product;
import com.xbs.flowcart.catalog.domain.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductRepositoryAdapter implements ProductRepository {

    @Override
    public void save(Product product) {

    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public void modify(Product product) {

    }

    @Override
    public void deleteById(UUID productId) {

    }
}
