package org.example.baitap_26_9_2025.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "home"; // src/main/resources/templates/home.html
    }

    @GetMapping("/products")
    public String productsPage() {
        return "products"; // products.html
    }

    @GetMapping("/users")
    public String usersPage() {
        return "users"; // users.html
    }

    @GetMapping("/categories")
    public String categoriesPage() {
        return "categories"; // categories.html
    }
}
