package com.psuti.rvprs.service;

import com.psuti.rvprs.dto.ProductDto;

import java.util.List;
import java.util.Optional;

/**
 * @author Modenov D.A.
 */

public interface ProductService {

    /**
     *
     * @return
     */
    List<ProductDto> getAll();

    /**
     *
     * @param productId
     * @return
     */
    Optional<ProductDto> getById(Long productId);

    /**
     *
     * @param productDto
     * @return
     */
    Optional<ProductDto> add(ProductDto productDto);

    /**
     *
     * @param productId
     * @param productDto
     * @return
     */
    boolean update(Long productId, ProductDto productDto);

    /**
     *
     * @param productId
     * @return
     */
    boolean delete(Long productId);

}
