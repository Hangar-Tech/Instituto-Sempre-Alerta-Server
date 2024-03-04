package com.institutosemprealerta.semprealerta.application.service.impl;

import com.institutosemprealerta.semprealerta.domain.ports.out.UserRepository;
import com.institutosemprealerta.semprealerta.domain.service.impl.UserServiceImpl;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User userValid = UserMocks.returnValidUserEntity();

        lenient().when(userRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(userValid));
        lenient().when(userRepository.findByRegistration(ArgumentMatchers.anyString())).thenReturn(Optional.of(userValid));
        lenient().when(userRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(Optional.of(userValid));
        lenient().doNothing().when(userRepository).save(ArgumentMatchers.any(User.class));
        lenient().doNothing().when(userRepository).update(ArgumentMatchers.anyInt(), ArgumentMatchers.any(User.class));
        lenient().doNothing().when(userRepository).delete(ArgumentMatchers.anyInt());
    }

    @AfterEach
    void tearDown() {
        reset(userRepository);
    }

    @Test
    @DisplayName("Should Save User Successfully")
    void should_Save_User_Successfully() {
        User userToCreate = UserMocks.returnValidUserToCreate();
        assertDoesNotThrow(() -> userService.save(userToCreate));
    }

    @Test
    @DisplayName("Should Update User Successfully")
    void should_update_User_Successfully() {
        User userToUpdate = UserMocks.returnValidUserToUpdate();
        assertDoesNotThrow(() -> userService.update(1, userToUpdate));
    }

    @Test
    @DisplayName("Should Delete User Successfully")
    void should_Delete_User_Successfully() {
        assertDoesNotThrow(() -> userService.delete(1));
    }

    @Test
    @DisplayName("Should Find User By Registration Successfully")
    void should_findUserByRegistration_Successfully() {
        User expectedUser = UserMocks.returnValidUserEntity();
        User userFound = userService.findByRegistration(expectedUser.getRegistration());

        assertEquals(expectedUser.getRegistration(), userFound.getRegistration());
        assertEquals(expectedUser.getName(), userFound.getName());
        assertNotNull(userFound.getId());
    }

    @Test
    @DisplayName("Should Find User By Email Successfully")
    void should_findUserByEmail_Successfully() {
        User expectedUser = UserMocks.returnValidUserEntity();
        User userFound = userService.findByEmail(expectedUser.getContact().getEmail());

        assertEquals(expectedUser.getContact().getEmail(), userFound.getContact().getEmail());
        assertEquals(expectedUser.getName(), userFound.getName());
        assertNotNull(userFound.getId());
    }

    @Test
    @DisplayName("Should Find User By Id Successfully")
    void should_findUserById_Successfully() {
        User expectedUser = UserMocks.returnValidUserEntity();
        User userFound = userService.findById(1);

        assertEquals(expectedUser.getRegistration(), userFound.getRegistration());
        assertEquals(expectedUser.getName(), userFound.getName());
        assertNotNull(userFound.getId());
    }
}