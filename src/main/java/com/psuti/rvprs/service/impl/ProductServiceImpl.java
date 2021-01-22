package com.psuti.rvprs.service.impl;

import com.psuti.rvprs.dto.ProductDto;
import com.psuti.rvprs.mapper.ProductMapper;
import com.psuti.rvprs.model.Product;
import com.psuti.rvprs.repository.ProductRepository;
import com.psuti.rvprs.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Modenov D.A.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            return Collections.emptyList();
        }

        return products.stream()
                .map(ProductMapper.INSTANCE::toDto)
                .sorted(Comparator.comparing(ProductDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> getById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        return product.map(ProductMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<ProductDto> add(ProductDto productDto) {
        if (Objects.isNull(productDto)) {
            return Optional.empty();
        }

        Product product = productRepository.save(ProductMapper.INSTANCE.toModel(productDto));

        return Optional.of(ProductMapper.INSTANCE.toDto(product));
    }

    @Override
    @Transactional
    public boolean update(Long productId, ProductDto productDto) {
        if (Objects.isNull(productId) || Objects.isNull(productDto)) {
            return false;
        }

        Product product = productRepository.findById(productId).orElse(null);

        if (Objects.isNull(product)) {
            return false;
        }

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setShelf(productDto.getShelf());

        productRepository.save(product);

        return true;
    }

    @Override
    public boolean delete(Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (!product.isPresent()) {
            return false;
        }

        productRepository.delete(product.get());
        return true;
    }

}
