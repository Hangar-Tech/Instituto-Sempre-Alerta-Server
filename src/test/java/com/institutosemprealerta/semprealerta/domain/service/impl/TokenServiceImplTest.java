package com.institutosemprealerta.semprealerta.domain.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.Contact;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.mocks.UserMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Token service")
class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Mock
    User user;

    @Mock
    Contact contact;

    private final String secret = "secret";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(tokenService, "secret", secret);

        when(user.getContact()).thenReturn(contact);
        when(user.getContact().getEmail())
                .thenReturn(UserMocks.returnValidContact().getEmail());
    }

    @AfterEach
    void turnDown() {
        reset(user, contact);
    }

    @Test
    @DisplayName("Should generate token successfully")
    void should_GenerateToken_Successfully() {
        String token = tokenService.generateToken(user);

        assertNotNull(token);
    }

    @Test
    @DisplayName("Should validate token successfully")
    void should_ValidateToken_Successfully() {
        String token = tokenService.generateToken(user);
        String subject = tokenService.validateToken(token);

        assertEquals(user.getContact().getEmail(), subject);
    }

    @Test
    @DisplayName("Should validate token with failure")
    void should_ValidateToken_With_Failure() {
        String invalidToken = "where are you now?";
        String invalidSubject = tokenService.validateToken(invalidToken);

        assertTrue(invalidSubject.isBlank());
    }
}