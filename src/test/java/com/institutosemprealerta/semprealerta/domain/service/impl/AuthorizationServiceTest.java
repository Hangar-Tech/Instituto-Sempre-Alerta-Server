package com.institutosemprealerta.semprealerta.domain.service.impl;

import com.institutosemprealerta.semprealerta.domain.service.UserService;
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
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Authorization Service")
class AuthorizationServiceTest {

    @InjectMocks
    private AuthorizationService authorizationService;

    @Mock
    private UserService userService;

    private final User validUserEntity = UserMocks.returnValidUserEntity();

    @BeforeEach
    void setUp() {
        when(userService.findByEmail(anyString()))
                .thenReturn(validUserEntity);
    }

    @AfterEach
    void tearDown() {
        reset(userService);
    }

    @Test
    @DisplayName("Should load user by username successfully")
    void should_LoadUserByUsername_Successfully() {
        UserDetails response = authorizationService.loadUserByUsername(validUserEntity.getContact().getEmail());

        assertNotNull(response);
    }
}