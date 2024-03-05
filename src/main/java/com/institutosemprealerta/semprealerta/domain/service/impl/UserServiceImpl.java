package com.institutosemprealerta.semprealerta.domain.service.impl;

import com.institutosemprealerta.semprealerta.domain.service.UserService;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.user.UserNotFoundException;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.domain.ports.out.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        String newPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(newPassword);
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
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User findById(int id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
