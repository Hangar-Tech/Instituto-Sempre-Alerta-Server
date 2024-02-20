package com.institutosemprealerta.semprealerta.domain.ports.out;

import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(int id);
    void update(int id, User user);
    void delete(int id);
    Optional<User> findByRegistration(int registration);
    Optional<User> findByEmail(String email);

}
