package org.example.baitap_26_9_2025.service;

import org.example.baitap_26_9_2025.entity.Product;
import org.example.baitap_26_9_2025.entity.User;
import org.example.baitap_26_9_2025.repository.ProductRepository;
import org.example.baitap_26_9_2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired private ProductRepository productRepository;
    @Autowired private UserRepository userRepository;

    public List<Product> getAllSortedByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    public List<Product> getByCategory(Long categoryId) {
        return productRepository.findProductsByCategoryId(categoryId);
    }

    public Product createProduct(String title, int quantity, String description, Double price, Long userId) {
        User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product p = Product.builder()
                .title(title)
                .quantity(quantity)
                .description(description)
                .price(price)
                .user(u)
                .build();
        return productRepository.save(p);
    }

    public Product updateProduct(Long id, String title, int quantity, String description, Double price) {
        Product p = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        p.setTitle(title);
        p.setQuantity(quantity);
        p.setDescription(description);
        p.setPrice(price);
        return productRepository.save(p);
    }

    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }

    public List<Product> findAll() { return productRepository.findAll(); }
    public Product findById(Long id) { return productRepository.findById(id).orElse(null); }
}
