package com.institutosemprealerta.semprealerta.infrastructure.entity.user;

import com.institutosemprealerta.semprealerta.domain.ports.out.request.LoginDTO;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.LoginResponse;

public class LoginFactory {
    public static final LoginFactory INSTANCE = new LoginFactory();

    private LoginFactory() {}

    public LoginResponse createNewLoginResponse(String token) {
        return new LoginResponse(token);
    }

    public LoginDTO createNewLoginDTO(String email, String password) {
        return new LoginDTO(email, password);
    }
}
