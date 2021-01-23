package com.psuti.rvprs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/labs/second")
public class ProductController {

    @GetMapping
    public String getPage() {

        return "products";
    }
}
