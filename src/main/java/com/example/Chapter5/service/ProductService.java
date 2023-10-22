package com.example.Chapter5.service;

import com.example.Chapter5.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    // Metode untuk menambahkan product
    public boolean addProduct(Product product);

    // Metode untuk mengupdate detail product
    public Product updateProduct(Product product);

    // Metode untuk menghapus product
    public void deleteProduct(String id);

    // Metode untuk menampilkan product yang tersedia
    public List<Product> getAvailableProducts();
}
