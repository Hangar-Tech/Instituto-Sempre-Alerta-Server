package com.institutosemprealerta.semprealerta.domain.ports.out;

import com.institutosemprealerta.semprealerta.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(int id);
    void update(int id, User user);
    void delete(int id);

}
