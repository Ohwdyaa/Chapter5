package com.example.Chapter5.service;

import com.example.Chapter5.model.Product;
import com.example.Chapter5.model.User;
import com.example.Chapter5.repository.ProductRepository;
import com.example.Chapter5.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        // Anda dapat melakukan inisialisasi atau persiapan lain yang diperlukan di sini.
    }

    //  Test Add
    @Test
    public void testAddProduct() {
        // Membangun objek User dengan menggunakan builder pattern
        Product product = Product.builder()
                .productCode(1L)
                .name("Nasi + Ayam")
                .price(18000)
                .available(true)
                .build();

        // Memanggil method addUser
        boolean result = productService.addProduct(product);

        // Memeriksa apakah hasilnya adalah true (sukses)
        assertTrue(result);

        // Memeriksa apakah pengguna telah ditambahkan ke database dengan benar
        Product savedUser = productRepository.findById(product.getProductCode()).orElse(null);
        assertTrue(savedUser != null);
        // Memeriksa apakah atribut-atribut pengguna sesuai dengan yang diharapkan
        // Assert.assertEquals(user.getUserName(), savedUser.getUserName());
    }

    @Test
    public void testAddProductWithNullProduct() {
        // Memanggil method addUser dengan parameter user null
        boolean result = productService.addProduct(null);

        // Memeriksa apakah hasilnya adalah false (gagal)
        assertFalse(result);
    }

    @Test
    public void testGetAvailableProducts() {
        // Membuat objek produk dengan builder
        Product product1 = Product.builder()
                .name("Nasi Goreng")
                .price(15000)
                .available(true)
                .build();

        Product product2 = Product.builder()
                .name("Mie Goreng")
                .price(13000)
                .available(false)
                .build();

        Product product3 = Product.builder()
                .name("Soto")
                .price(20000)
                .available(true)
                .build();

        // Menyimpan produk ke basis data
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        // Memanggil metode yang akan diuji
        List<Product> availableProducts = productService.getAvailableProducts();

        // Mengharapkan hasil yang sesuai dengan produk yang tersedia
        assertEquals(2, availableProducts.size()); // Anda dapat menyesuaikan dengan jumlah produk yang diharapkan.
    }
}
