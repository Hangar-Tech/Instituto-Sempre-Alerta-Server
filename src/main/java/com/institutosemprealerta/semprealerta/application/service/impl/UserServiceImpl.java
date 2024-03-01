package com.institutosemprealerta.semprealerta.application.service.impl;

import com.institutosemprealerta.semprealerta.application.service.UserService;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.domain.ports.out.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void update(int id, User user) {
        this.userRepository.update(id, user);
    }

    @Override
    public void delete(int id) {
        this.userRepository.delete(id);
    }

    @Override
    public User findByRegistration(String registration) {
        return this.userRepository.findByRegistration(registration)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findById(int id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}