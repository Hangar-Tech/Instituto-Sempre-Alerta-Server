package com.institutosemprealerta.semprealerta.domain.service.impl;

import com.institutosemprealerta.semprealerta.domain.ports.out.request.LoginDTO;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.LoginResponse;
import com.institutosemprealerta.semprealerta.domain.service.AuthenticationService;
import com.institutosemprealerta.semprealerta.domain.service.TokenService;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public LoginResponse login(LoginDTO login) {
        UsernamePasswordAuthenticationToken userNamePassword =
                new UsernamePasswordAuthenticationToken(login.email(), login.password());
        Authentication auth = this.authenticationManager.authenticate(userNamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponse(token);
    }

    @Override
    public void register(User user) {

    }
}
