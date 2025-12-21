package com.xbs.flowcart.catalog.infrastructure.api;

import com.xbs.flowcart.catalog.CatalogApi;
import com.xbs.flowcart.catalog.application.dto.ProductDto;
import com.xbs.flowcart.catalog.application.usecases.find.FindProductHandler;
import com.xbs.flowcart.catalog.application.usecases.find.FindProductQuery;
import com.xbs.flowcart.catalog.application.usecases.find.FindProductQueryResponse;
import com.xbs.flowcart.catalog.model.CreateProductRequest;
import com.xbs.flowcart.catalog.model.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class CatalogController implements CatalogApi {

    private final FindProductHandler findProductHandler;

    @Override
    public void createProduct(CreateProductRequest request) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ResponseEntity<ProductResponse> getProduct(UUID id) {

        FindProductQuery query = new FindProductQuery(id);

        FindProductQueryResponse response = findProductHandler.handle(query);

        ProductDto dto = response.product();

        ProductResponse contractResponse = new ProductResponse(
                dto.id(),
                dto.name(),
                dto.shortDescription(),
                dto.longDescription(),
                dto.price(),
                dto.currency(),
                dto.stock(),
                dto.category(),
                dto.active()
        );

        return ResponseEntity.ok(contractResponse);
    }
}
