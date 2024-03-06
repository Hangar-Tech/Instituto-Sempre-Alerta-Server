package com.institutosemprealerta.semprealerta.domain.service;

import com.institutosemprealerta.semprealerta.domain.ports.out.request.LoginDTO;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.LoginResponse;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;

public interface AuthenticationService {
    LoginResponse login(LoginDTO login);
    void register(User user);
}
