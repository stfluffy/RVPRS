package com.rvprs.client.api;

import com.rvprs.client.ProductServiceRMI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RMIRestController {

    private final ProductServiceRMI productServiceRMI;



}
