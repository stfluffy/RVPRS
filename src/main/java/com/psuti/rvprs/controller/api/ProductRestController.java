package com.psuti.rvprs.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.psuti.rvprs.dto.ProductDto;
import com.psuti.rvprs.dto.View;
import com.psuti.rvprs.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Modenov D.A.
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    /**
     *
     * <p>
     *
     * @param id
     * @return
     */
    @GetMapping("/products/{productId}")
    @JsonView(View.Public.class)
    public ResponseEntity<ProductDto> getById(@PathVariable(name = "productId") Long id) {
        Optional<ProductDto> productDto = productService.getById(id);

        return productDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     *
     * <p>
     *
     * @return
     */
    @GetMapping("/products")
    @JsonView(View.Public.class)
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> productDtos = productService.getAll();

        return !productDtos.isEmpty() ?
                ResponseEntity.ok(productDtos) : ResponseEntity.notFound().build();
    }

    /**
     *
     * <p>
     *
     * @param productDto
     * @return
     */
    @PostMapping("/products")
    @JsonView(View.Public.class)
    public ResponseEntity<?> add(@JsonView(View.Update.class) @RequestBody ProductDto productDto,
                                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach(e -> errors.append(e.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errors.toString());
        }

        Optional<ProductDto> productAdd = productService.add(productDto);

        return productAdd.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     *
     * <p>
     *
     * @param id
     * @param productDto
     * @return
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<?> update(@PathVariable(name = "productId") Long id,
                                    @JsonView(View.Update.class) @RequestBody ProductDto productDto,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach(e -> errors.append(e.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errors.toString());
        }

        boolean updateProduct = productService.update(id, productDto);

        return updateProduct ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    /**
     *
     * <p>
     *
     * @param id
     * @return
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> delete(@PathVariable(name = "productId") Long id) {
        boolean deleteProduct = productService.delete(id);

        return deleteProduct ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

}
