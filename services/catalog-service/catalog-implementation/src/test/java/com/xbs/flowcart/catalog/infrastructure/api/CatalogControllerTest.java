package com.xbs.flowcart.catalog.infrastructure.api;

import com.xbs.flowcart.catalog.application.dto.ProductDto;
import com.xbs.flowcart.catalog.application.usecases.find.FindProductHandler;
import com.xbs.flowcart.catalog.application.usecases.find.FindProductQuery;
import com.xbs.flowcart.catalog.application.usecases.find.FindProductQueryResponse;
import com.xbs.flowcart.catalog.model.CreateProductRequest;
import com.xbs.flowcart.catalog.model.ProductResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogControllerTest {

    @Mock
    private FindProductHandler findProductHandler;

    @InjectMocks
    private CatalogController catalogController;

    @Test
    void shouldReturnProductResponseWhenProductExists() {
        UUID productId = UUID.randomUUID();
        ProductDto productDto = new ProductDto(
                productId,
                "Test Product",
                "Short Desc",
                "Long Desc",
                BigDecimal.TEN,
                "USD",
                100,
                "Electronics",
                true
        );
        FindProductQueryResponse queryResponse = new FindProductQueryResponse(productDto);

        when(findProductHandler.handle(any(FindProductQuery.class))).thenReturn(queryResponse);

        ResponseEntity<ProductResponse> response = catalogController.getProduct(productId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(productId, response.getBody().id());
        assertEquals("Test Product", response.getBody().name());
        assertEquals("Short Desc", response.getBody().shortDescription());
        assertEquals(BigDecimal.TEN, response.getBody().price());

        verify(findProductHandler).handle(any(FindProductQuery.class));
    }

    @Test
    void shouldThrowUnsupportedOperationExceptionWhenCreateProduct() {
        CreateProductRequest request = new CreateProductRequest("Producto", "Descripcion", "Descripcion larga", new BigDecimal(20), "USD", 20, "Categoria");

        assertThrows(UnsupportedOperationException.class, () -> catalogController.createProduct(request));
    }
}