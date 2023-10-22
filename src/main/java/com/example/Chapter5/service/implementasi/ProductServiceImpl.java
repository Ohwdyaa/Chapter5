package com.example.Chapter5.service.implementasi;

import com.example.Chapter5.model.Product;
import com.example.Chapter5.repository.ProductRepository;
import com.example.Chapter5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public boolean addProduct(Product product) {
        return Optional.ofNullable(product)
                .map(newUsers -> productRepository.save(newUsers))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productRepository.findById(product.getProductCode())
                .map(productRepository::save)
                .isPresent();
    }

    @Override
    public boolean deleteProduct(Long productCode) {
        productRepository.deleteById(productCode);
        return true;
    }

    @Override
    public List<Product> getAvailableProducts() {
        return productRepository.findAvailableProducts();
    }
}
