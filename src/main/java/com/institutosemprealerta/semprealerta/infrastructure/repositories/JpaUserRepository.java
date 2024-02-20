package com.institutosemprealerta.semprealerta.infrastructure.repositories;

import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.contact.email = ?1")
    Optional<User> findByEmail(String email);

    Optional<User> findByRegistration(String registration);
}
