package com.institutosemprealerta.semprealerta.repository;

import com.institutosemprealerta.semprealerta.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
