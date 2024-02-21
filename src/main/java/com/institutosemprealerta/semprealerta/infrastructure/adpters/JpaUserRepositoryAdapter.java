package com.institutosemprealerta.semprealerta.infrastructure.adpters;

import com.institutosemprealerta.semprealerta.domain.ports.out.UserRepository;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.infrastructure.repositories.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository userRepository;

    public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.userRepository = jpaUserRepository;
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public Optional<User> findById(int id) {
        return this.userRepository.findById(id);
    }

    @Override
    public void update(int id, User user) {
        User userToUpdate = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setId(userToUpdate.getId());
        user.setRegistration(userToUpdate.getRegistration());

        this.userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        User userToDelete = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        this.userRepository.delete(userToDelete);
    }

    @Override
    public Optional<User> findByRegistration(String registration) {
        return this.userRepository.findByRegistration(registration);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }


}
