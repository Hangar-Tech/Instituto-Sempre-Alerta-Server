package com.institutosemprealerta.semprealerta.domain.service;

import com.institutosemprealerta.semprealerta.domain.model.UserDTO;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;

public interface TokenService {
    String generateToken(User user);
    String validateToken(String token);
}
