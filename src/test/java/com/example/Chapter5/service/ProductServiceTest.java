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
        // Membuat daftar produk untuk pengujian
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().productCode(2L).name("Product 1").available(true).build());
        products.add(Product.builder().productCode(3L).name("Product 2").available(false).build());
        products.add(Product.builder().productCode(4L).name("Product 3").available(true).build());

        // Memanggil metode yang akan diuji
        List<Product> availableProducts = productService.getAvailableProducts();

        // Menggunakan Java Stream untuk menyaring produk yang tersedia
        List<Product> expectedProducts = products.stream()
                .filter(Product::getAvailable) // Menggunakan metode isAvailable() untuk mengecek status
                .collect(Collectors.toList());

        // Memeriksa apakah daftar produk yang dikembalikan sesuai dengan yang diharapkan
        assertEquals(expectedProducts, availableProducts);
    }
}
