package com.xbs.flowcart.catalog.infrastructure.persistence.adapter;

import com.xbs.flowcart.catalog.domain.Product;
import com.xbs.flowcart.catalog.domain.repository.ProductRepository;
import com.xbs.flowcart.catalog.infrastructure.persistence.mapper.ProductPersistenceMapper;
import com.xbs.flowcart.catalog.infrastructure.persistence.persistence.SpringDataMongoProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final SpringDataMongoProductRepository mongoRepository;
    private final ProductPersistenceMapper mapper;

    @Override
    public void save(Product product) {
        mongoRepository.save(mapper.toDocument(product));
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return mongoRepository.findById(productId).map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return mongoRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID productId) {
        mongoRepository.deleteById(productId);
    }
}
