package com.psuti.rvprs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Modenov D.A.
 */

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index() {

        return "index";
    }
}
