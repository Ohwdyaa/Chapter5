package com.example.Chapter5.service.implementasi;

import com.example.Chapter5.model.User;
import com.example.Chapter5.repository.UserRepository;
import com.example.Chapter5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public boolean addUser(User user) {
        return Optional.ofNullable(user)
                .map(newUsers -> userRepository.save(newUsers))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    public boolean updateUser(User user) {
        return userRepository.findById(user.getUserId())
                .map(userRepository::save)
                .isPresent();
    }

    @Override
    public boolean deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return true;
    }
}
