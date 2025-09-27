package org.example.baitap_26_9_2025.service;

import org.example.baitap_26_9_2025.entity.Category;
import org.example.baitap_26_9_2025.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;

    public Category create(Category c) { return categoryRepository.save(c); }
    public Category update(Long id, Category input) {
        Category ex = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        ex.setName(input.getName());
        ex.setImages(input.getImages());
        return categoryRepository.save(ex);
    }
    public boolean delete(Long id) { categoryRepository.deleteById(id); return true; }
    public List<Category> findAll() { return categoryRepository.findAll(); }
    public Category findById(Long id) { return categoryRepository.findById(id).orElse(null); }
}
