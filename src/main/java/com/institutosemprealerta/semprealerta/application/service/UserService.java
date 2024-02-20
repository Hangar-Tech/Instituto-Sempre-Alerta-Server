package com.institutosemprealerta.semprealerta.application.service;

import com.institutosemprealerta.semprealerta.domain.user.User;

public interface UserService {
    void save(User user);
    void update(int id, User user);
    void delete(int id);
    User findByRegistration(int registration);
    User findByEmail(String email);
}
