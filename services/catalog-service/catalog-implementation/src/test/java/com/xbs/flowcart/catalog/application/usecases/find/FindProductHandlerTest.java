package com.xbs.flowcart.catalog.application.usecases.find;

import com.xbs.flowcart.catalog.application.dto.ProductDto;
import com.xbs.flowcart.catalog.domain.entity.Product;
import com.xbs.flowcart.catalog.domain.entity.valueobject.Category;
import com.xbs.flowcart.catalog.domain.entity.valueobject.Description;
import com.xbs.flowcart.catalog.domain.entity.valueobject.Price;
import com.xbs.flowcart.catalog.domain.entity.valueobject.StockQuantity;
import com.xbs.flowcart.catalog.domain.exception.ProductNotFoundException;
import com.xbs.flowcart.catalog.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindProductHandlerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private FindProductHandler findProductHandler;

    @Test
    void shouldReturnFindProductQueryResponseWhenProductExists() {
        UUID productId = UUID.randomUUID();
        FindProductQuery query = new FindProductQuery(productId);
        Product product = Product.builder()
                .id(productId)
                .name("Test Product")
                .description(new Description("Short", "Long"))
                .price(new Price(Currency.getInstance("USD"), BigDecimal.TEN))
                .stockQuantity(new StockQuantity(5))
                .category(Category.LAPTOP)
                .isActive(true)
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        FindProductQueryResponse response = findProductHandler.handle(query);

        assertNotNull(response);
        ProductDto dto = response.product();
        assertEquals(productId, dto.id());
        assertEquals("Test Product", dto.name());
        assertEquals("Short", dto.shortDescription());

        verify(productRepository).findById(productId);
    }

    @Test
    void shouldThrowProductNotFoundExceptionWhenProductDoesNotExist() {
        UUID productId = UUID.randomUUID();
        FindProductQuery query = new FindProductQuery(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> findProductHandler.handle(query));

        verify(productRepository).findById(productId);
    }
}