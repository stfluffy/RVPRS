package com.psuti.rvprs.rmi.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psuti.rvprs.dto.ProductDto;
import com.psuti.rvprs.rmi.ProductServiceRMI;
import com.psuti.rvprs.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceRMIImpl implements ProductServiceRMI {

    private final ProductService productService;

    @Override
    public String getAll() {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(productService.getAll());
        } catch (Exception ignore) {
            return null;
        }

    }

    @Override
    public String getById(Long id) {
        Optional<ProductDto> productDto = productService.getById(id);

        ObjectMapper objectMapper = new ObjectMapper();

        if (productDto.isPresent()) {
            try {
                return objectMapper.writeValueAsString(productDto.get());
            } catch (Exception ignore) {
                return null;
            }
        }

        return null;
    }
}
