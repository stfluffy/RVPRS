package com.rvprs.client.api;

import com.rvprs.client.rmiinterface.ProductServiceRMI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RMIRestController {

    private final ProductServiceRMI productServiceRMI;


    @GetMapping
    public ResponseEntity<?> getAll() {

        return ResponseEntity
                .ok(productServiceRMI.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {

        return ResponseEntity.ok(productServiceRMI.getById(id));
    }
}
