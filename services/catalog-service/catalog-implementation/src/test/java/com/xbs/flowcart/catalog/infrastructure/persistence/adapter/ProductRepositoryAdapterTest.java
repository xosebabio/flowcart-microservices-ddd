package com.xbs.flowcart.catalog.infrastructure.persistence.adapter;

import com.xbs.flowcart.catalog.domain.Product;
import com.xbs.flowcart.catalog.infrastructure.persistence.document.ProductDocument;
import com.xbs.flowcart.catalog.infrastructure.persistence.mapper.ProductPersistenceMapper;
import com.xbs.flowcart.catalog.infrastructure.persistence.repository.SpringDataMongoProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryAdapterTest {

    @Mock
    private SpringDataMongoProductRepository mongoRepository;

    @Mock
    private ProductPersistenceMapper mapper;

    @InjectMocks
    private ProductRepositoryAdapter adapter;

    @Test
    void shouldSaveProduct() {
        Product product = mock(Product.class);
        ProductDocument document = mock(ProductDocument.class);

        when(mapper.toDocument(product)).thenReturn(document);

        adapter.save(product);

        verify(mongoRepository).save(document);
    }

    @Test
    void shouldFindById() {
        UUID id = UUID.randomUUID();
        ProductDocument document = mock(ProductDocument.class);
        Product product = mock(Product.class);

        when(mongoRepository.findById(id)).thenReturn(Optional.of(document));
        when(mapper.toDomain(document)).thenReturn(product);

        Optional<Product> result = adapter.findById(id);

        assertThat(result).isPresent().contains(product);
    }

    @Test
    void shouldReturnEmptyWhenProductNotFound() {
        UUID id = UUID.randomUUID();
        when(mongoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Product> result = adapter.findById(id);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldFindAll() {
        ProductDocument doc1 = mock(ProductDocument.class);
        ProductDocument doc2 = mock(ProductDocument.class);
        Product prod1 = mock(Product.class);
        Product prod2 = mock(Product.class);

        when(mongoRepository.findAll()).thenReturn(List.of(doc1, doc2));
        when(mapper.toDomain(doc1)).thenReturn(prod1);
        when(mapper.toDomain(doc2)).thenReturn(prod2);

        List<Product> result = adapter.findAll();

        assertThat(result).hasSize(2).containsExactly(prod1, prod2);
    }

    @Test
    void shouldDeleteById() {
        UUID id = UUID.randomUUID();

        adapter.deleteById(id);

        verify(mongoRepository).deleteById(id);
    }
}