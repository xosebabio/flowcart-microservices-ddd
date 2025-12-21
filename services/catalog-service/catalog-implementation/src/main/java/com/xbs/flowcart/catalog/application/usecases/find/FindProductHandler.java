package com.xbs.flowcart.catalog.application.usecases.find;

import com.xbs.flowcart.catalog.application.assembler.ProductAssembler;
import com.xbs.flowcart.catalog.application.dto.ProductDto;
import com.xbs.flowcart.catalog.domain.entity.Product;
import com.xbs.flowcart.catalog.domain.exception.ProductNotFoundException;
import com.xbs.flowcart.catalog.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FindProductHandler {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public FindProductQueryResponse handle(FindProductQuery query) {

        Product product = productRepository.findById(query.id())
                .orElseThrow(() -> new ProductNotFoundException(query.id()));

        ProductDto productDto = ProductAssembler.toDto(product);

        return new FindProductQueryResponse(productDto);
    }
}