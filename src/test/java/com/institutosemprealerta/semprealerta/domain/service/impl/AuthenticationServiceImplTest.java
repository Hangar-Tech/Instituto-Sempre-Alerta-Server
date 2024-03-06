package com.institutosemprealerta.semprealerta.domain.service.impl;

import com.institutosemprealerta.semprealerta.domain.ports.out.request.LoginDTO;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.LoginResponse;
import com.institutosemprealerta.semprealerta.domain.service.TokenService;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.Contact;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.mocks.UserMocks;
import org.springframework.security.core.Authentication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Authentication service")
class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    private final Contact contact = UserMocks.returnValidContact();
    private LoginDTO loginDTO;

    private final String token = "mockToken";

    @BeforeEach
    void setUp() {
        loginDTO = new LoginDTO(contact.getEmail(), "123");
        User user = new User();
        user.setContact(contact);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null);
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);


        when(tokenService.generateToken(any(User.class))).thenReturn(token);

    }

    @AfterEach
    void tearDown() {
        reset(tokenService, authenticationManager);
    }

    @Test
    void login() {
        LoginResponse response = authenticationService.login(loginDTO);
        assertEquals(token, response.accessToken());
    }
}