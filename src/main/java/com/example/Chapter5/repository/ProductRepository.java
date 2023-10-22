package com.example.Chapter5.repository;

import com.example.Chapter5.model.Product;
import com.example.Chapter5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);
    void deleteById(Long productCode);

    @Query("SELECT p FROM Product p WHERE p.available = true")
    List<Product> findAvailableProducts();
}
