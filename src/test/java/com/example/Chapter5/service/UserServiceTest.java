package com.example.Chapter5.service;

import com.example.Chapter5.model.User;
import com.example.Chapter5.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        // Anda dapat melakukan inisialisasi atau persiapan lain yang diperlukan di sini.
    }

//  Test Add
    @Test
    public void testAddUser() {
        // Membangun objek User dengan menggunakan builder pattern
        User user = User.builder()
                .userId(1L)
                .username("John")
                .email("john@gmail.com")
                .password("root")
                .build();

        // Memanggil method addUser
        boolean result = userService.addUser(user);

        // Memeriksa apakah hasilnya adalah true (sukses)
        assertTrue(result);

        // Memeriksa apakah pengguna telah ditambahkan ke database dengan benar
        User savedUser = userRepository.findById(user.getUserId()).orElse(null);
        assertTrue(savedUser != null);
        // Memeriksa apakah atribut-atribut pengguna sesuai dengan yang diharapkan
        // Assert.assertEquals(user.getUserName(), savedUser.getUserName());
    }

    @Test
    public void testAddUserWithNullUser() {
        // Memanggil method addUser dengan parameter user null
        boolean result = userService.addUser(null);

        // Memeriksa apakah hasilnya adalah false (gagal)
        assertFalse(result);
    }

// Test Update
    @Test
    public void testUpdateUser() {
    // Membangun objek User dengan menggunakan builder pattern dari Project Lombok
    User user = User.builder()
            .userId(1L)
            .username("John")
            .build();

    // Menyimpan pengguna ke database
    userRepository.save(user);

    // Memanggil method updateUser
    boolean result = userService.updateUser(user);

    // Memeriksa apakah hasilnya adalah true (sukses)
    assertTrue(result);

    // Memeriksa apakah pengguna telah diperbarui dengan benar
    User updatedUser = userRepository.findById(user.getUserId()).orElse(null);
    assertTrue(updatedUser != null);
    // Memeriksa apakah atribut pengguna telah diperbarui dengan benar
    assertTrue("John".equals(updatedUser.getUsername()));
}

    @Test
    public void testUpdateUserNotFound() {
        // Membangun objek User dengan menggunakan builder pattern dari Project Lombok
        User user = User.builder()
                .userId(1L)
                .username("John")
                .build();

        // Memanggil method updateUser ketika pengguna tidak ada di database
        boolean result = userService.updateUser(user);

        // Memeriksa apakah hasilnya adalah false (gagal karena pengguna tidak ditemukan)
        assertFalse(result);
    }

    @Test
    public void testDeleteUser() {
        // Membangun objek User dengan menggunakan builder pattern dari Project Lombok
        User user = User.builder()
                .userId(1L)
                .username("John")
                .build();

        // Menambahkan pengguna ke database
        userRepository.save(user);

        // Memanggil method deleteUser
        boolean result = userService.deleteUser(user.getUserId());

        // Memeriksa apakah hasilnya adalah true (sukses)
        assertTrue(result);

        // Memeriksa apakah pengguna telah dihapus dari database
        User deletedUser = userRepository.findById(user.getUserId()).orElse(null);
        assertTrue(deletedUser == null);
    }

//    @Test
//    public void testDeleteUserWithNonexistentUser() {
//        // Memanggil method deleteUser dengan ID pengguna yang tidak ada di database
//        boolean result = userService.deleteUser(1L);
//
//        // Memeriksa apakah hasilnya adalah false (gagal)
//        assertFalse(result);
//    }
}
