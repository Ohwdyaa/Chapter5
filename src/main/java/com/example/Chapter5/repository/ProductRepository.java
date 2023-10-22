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

    List<Product> getProductCode(Long productCode);

    @Query(nativeQuery = true, value = "select * from product where available = true")
    List<Product> getAvailableProducts();
}
