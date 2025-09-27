package org.example.baitap_26_9_2025.controller;

import org.example.baitap_26_9_2025.entity.Category;
import org.example.baitap_26_9_2025.entity.Product;
import org.example.baitap_26_9_2025.entity.User;
import org.example.baitap_26_9_2025.graphql.input.CategoryInput;
import org.example.baitap_26_9_2025.graphql.input.ProductInput;
import org.example.baitap_26_9_2025.graphql.input.UserInput;
import org.example.baitap_26_9_2025.service.CategoryService;
import org.example.baitap_26_9_2025.service.ProductService;
import org.example.baitap_26_9_2025.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphqlController {
    @Autowired
    private ProductService productService;
    @Autowired private UserService userService;
    @Autowired private CategoryService categoryService;

    // ---- Queries ----
    @QueryMapping
    public List<Product> allProductsSortedByPriceAsc() {
        return productService.getAllSortedByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productService.getByCategory(categoryId);
    }

    @QueryMapping public List<User> users() { return userService.findAll(); }
    @QueryMapping public User userById(@Argument Long id) { return userService.findById(id); }

    @QueryMapping public List<Category> categories() { return categoryService.findAll(); }
    @QueryMapping public Category categoryById(@Argument Long id) { return categoryService.findById(id); }

    @QueryMapping public List<Product> products() { return productService.findAll(); }
    @QueryMapping public Product productById(@Argument Long id) { return productService.findById(id); }

    // ---- Mutations ----
    @MutationMapping
    public User createUser(@Argument UserInput input) {
        User u = User.builder()
                .fullname(input.fullname())
                .email(input.email())
                .password(input.password())
                .phone(input.phone())
                .build();
        return userService.create(u);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput input) {
        User u = new User();
        u.setFullname(input.fullname());
        u.setEmail(input.email());
        u.setPassword(input.password());
        u.setPhone(input.phone());
        return userService.update(id, u);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) { return userService.delete(id); }

    @MutationMapping
    public Category createCategory(@Argument CategoryInput input) {
        Category c = Category.builder().name(input.name()).images(input.images()).build();
        return categoryService.create(c);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument CategoryInput input) {
        Category c = new Category();
        c.setName(input.name());
        c.setImages(input.images());
        return categoryService.update(id, c);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) { return categoryService.delete(id); }

    @MutationMapping
    public Product createProduct(@Argument ProductInput input) {
        return productService.createProduct(input.title(), input.quantity(), input.description(), input.price(), input.userId());
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductInput input) {
        return productService.updateProduct(id, input.title(), input.quantity(), input.description(), input.price());
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) { return productService.deleteProduct(id); }
}
