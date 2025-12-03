package com.xbs.flowcart.catalog.infrastructure.persistence.repository;

import com.xbs.flowcart.catalog.infrastructure.persistence.document.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataMongoProductRepository extends MongoRepository<ProductDocument, UUID> {
}