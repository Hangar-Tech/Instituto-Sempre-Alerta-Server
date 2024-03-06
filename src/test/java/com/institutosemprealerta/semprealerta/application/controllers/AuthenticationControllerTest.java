package com.institutosemprealerta.semprealerta.application.controllers;

import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.user.UserNotFoundException;
import com.institutosemprealerta.semprealerta.domain.ports.out.request.LoginDTO;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.LoginResponse;
import com.institutosemprealerta.semprealerta.domain.service.AuthenticationService;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.LoginFactory;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.mocks.UserMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthenticationController")
class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    private final LoginResponse token = LoginFactory.INSTANCE.createNewLoginResponse("this-is-the-way");
    private final LoginDTO userCredentials = UserMocks.returnValidLoginDTO();

    @BeforeEach
    void setUp() {
        when(authenticationService.login(any(LoginDTO.class)))
                .thenReturn(token);
    }

    @AfterEach
    void tearDown() {
        reset(authenticationService);
    }

    @Test
    @DisplayName("Should login successfully")
    void should_Login_Successfully() {
        ResponseEntity<?> loginResponse = authenticationController.login(userCredentials);

        assertNotNull(loginResponse);
        assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
        assertEquals(token, loginResponse.getBody());
    }
    @Test
    @DisplayName("Should login with failure")
    void should_Login_With_Failure() {
        when(authenticationService.login(any(LoginDTO.class))).thenThrow(UserNotFoundException.class);

      assertThrows(UserNotFoundException.class, () -> authenticationController.login(userCredentials));
    }

}