package com.example.Chapter5.repository;

import com.example.Chapter5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "select * from users where user_id = ?1")
    List<User> getUserById(Long userId);

    User save(User users);

    void deleteById(Long userId);
}
