package com.example.Chapter5.service;

import com.example.Chapter5.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    // Metode untuk menambahkan user
    public boolean addUser(User user);

    // Metode untuk update user
    public boolean updateUser(User user);

    // Metode untuk menghapus user
    public boolean deleteUser(Long userId);
}
