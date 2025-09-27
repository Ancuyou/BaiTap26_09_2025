package org.example.baitap_26_9_2025.service;

import org.example.baitap_26_9_2025.entity.User;
import org.example.baitap_26_9_2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User u) { return userRepository.save(u); }
    public User update(Long id, User input) {
        User exist = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        exist.setFullname(input.getFullname());
        exist.setEmail(input.getEmail());
        exist.setPassword(input.getPassword());
        exist.setPhone(input.getPhone());
        return userRepository.save(exist);
    }
    public boolean delete(Long id) { userRepository.deleteById(id); return true; }
    public List<User> findAll() { return userRepository.findAll(); }
    public User findById(Long id) { return userRepository.findById(id).orElse(null); }
}