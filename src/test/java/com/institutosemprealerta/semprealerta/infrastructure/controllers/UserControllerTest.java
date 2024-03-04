package com.institutosemprealerta.semprealerta.infrastructure.controllers;

import com.institutosemprealerta.semprealerta.application.controllers.UserController;
import com.institutosemprealerta.semprealerta.domain.service.UserService;
import com.institutosemprealerta.semprealerta.domain.model.UserDTO;
import com.institutosemprealerta.semprealerta.domain.ports.out.responses.UserResponse;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.mocks.UserMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Controller")
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        User validUser = UserMocks.returnValidUserEntity();

        lenient().doNothing().when(userService).save(ArgumentMatchers.any(User.class));
        lenient().when(userService.findById(ArgumentMatchers.anyInt())).thenReturn(validUser);
        lenient().when(userService.findByRegistration(ArgumentMatchers.anyString())).thenReturn(validUser);
        lenient().doNothing().when(userService).update(ArgumentMatchers.anyInt(), ArgumentMatchers.any(User.class));
        lenient().doNothing().when(userService).delete(ArgumentMatchers.anyInt());

    }

    @AfterEach
    void tearDown() {
        reset(userService);
    }

    @Test
    @DisplayName("Should create user successfully")
    void should_CreateUser_Successfully() {
        UserDTO userDTO = UserMocks.returnValidUserDTO();

        ResponseEntity<?> response = userController.createUser(userDTO);

        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
        assertFalse(response.hasBody());
    }

    @Test
    @DisplayName("Should find user by id successfully")
    void should_FindUserById_Successfully() {
        ResponseEntity<UserResponse> response = userController.findById(1);
        User expectedUser = UserMocks.returnValidUserEntity();

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.hasBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(expectedUser.getName(), Objects.requireNonNull(response.getBody()).name());
    }

    @Test
    @DisplayName("Should find user by registration successfully")
    void should_findByUserRegistration_Successfully() {
        User expectedUser = UserMocks.returnValidUserEntity();
        String expectedRegistration = expectedUser.getRegistration();

        ResponseEntity<UserResponse> response = userController.findByRegistration(expectedRegistration);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertTrue(response.hasBody());
        assertEquals(expectedUser.getName(), Objects.requireNonNull(response.getBody()).name());
    }

    @Test
    @DisplayName("Should update user successfully")
    void should_updateUser_Successfully() {
        UserDTO userDTO = UserMocks.returnValidUserDTO();
        ResponseEntity<?> response = userController.updateUser(1, userDTO);

        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode().value());
        assertFalse(response.hasBody());
    }

    @Test
    @DisplayName("Should delete user successfully")
    void should_deleteUser_Successfully() {
        ResponseEntity<?> response = userController.deleteUser(1);

        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode().value());
        assertFalse(response.hasBody());
    }
}