package com.xbs.flowcart.catalog;

import com.xbs.flowcart.catalog.model.CreateProductRequest;
import com.xbs.flowcart.catalog.model.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.UUID;

@HttpExchange("/api/v1/products")
@Tag(name = "Catalog", description = "Catalog management operations")
public interface CatalogApi {

    @PostExchange
    @Operation(summary = "Create a new product", description = "Creates a new product in the catalog.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    void createProduct(@RequestBody CreateProductRequest request);

    @GetExchange("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieves a specific product by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    ResponseEntity<ProductResponse> getProduct(
            @Parameter(description = "Product UUID", required = true)
            @PathVariable("id") UUID id
    );
}