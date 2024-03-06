package com.institutosemprealerta.semprealerta.infrastructure.adpters;

import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.mocks.UserMocks;
import com.institutosemprealerta.semprealerta.infrastructure.repositories.JpaUserRepository;
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
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.reset;

@ExtendWith(MockitoExtension.class)
@DisplayName("JpaUserRepositoryAdapter")
class JpaUserRepositoryAdapterTest {

    @InjectMocks
    private JpaUserRepositoryAdapter jpaUserRepositoryAdapter;

    @Mock
    private JpaUserRepository userRepository;


    @BeforeEach
    void setUp() {
        User userValid = UserMocks.returnValidUserEntity();

        lenient().when(userRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(userValid));
        lenient().when(userRepository.findByRegistration(ArgumentMatchers.anyString())).thenReturn(Optional.of(userValid));
        lenient().when(userRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(Optional.of(userValid));
        lenient().when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(userValid);
        lenient().doNothing().when(userRepository).delete(ArgumentMatchers.any(User.class));
    }

    @AfterEach
    void tearDown() {
        reset(userRepository);
    }

    @Test
    @DisplayName("Should Save User Successfully")
    void should_Save_UserSuccessfully() {
        User userToCreate = UserMocks.returnValidUserToCreate();
        assertDoesNotThrow(() -> jpaUserRepositoryAdapter.save(userToCreate));
    }

    @Test
    @DisplayName("Should Find User By Id Successfully")
    void should_findUserById_Successfully() {
        User expectedUser = UserMocks.returnValidUserEntity();

        User user = jpaUserRepositoryAdapter.findById(1).orElse(new User());

        assertNotNull(user);
        assertEquals(expectedUser.getId(), user.getId());
        assertEquals(expectedUser.getRegistration(), user.getRegistration());
        assertEquals(expectedUser.getName(), user.getName());

    }

    @Test
    @DisplayName("Should Update A User Successfully")
    void should_Update_A_User_Successfully() {
        User userToUpdate = UserMocks.returnValidUserToUpdate();
        assertDoesNotThrow(() -> jpaUserRepositoryAdapter.update(1, userToUpdate));
    }

    @Test
    @DisplayName("Should Delete A User Successfully")
    void should_Delete_A_User_Successfully() {
        assertDoesNotThrow(() -> jpaUserRepositoryAdapter.delete(1));
    }

    @Test
    @DisplayName("Should Find User By Registration Successfully")
    void should_findUserByRegistration_Successfully() {
        User expectedUser = UserMocks.returnValidUserEntity();
        String expectedRegistration = expectedUser.getRegistration();
        User userFound = jpaUserRepositoryAdapter.findByRegistration(expectedRegistration).orElse(new User());

        assertNotNull(userFound);
        assertEquals(expectedRegistration, userFound.getRegistration());
        assertEquals(expectedUser.getName(), userFound.getName());
        assertNotNull(userFound.getId());
    }

    @Test
    @DisplayName("Should Find User By Email Successfully")
    void should_findUserByEmail_Successfully() {

        User expectedUser = UserMocks.returnValidUserEntity();
        String expectedEmail = expectedUser.getContact().getEmail();
        User userFound = jpaUserRepositoryAdapter.findByEmail(expectedEmail).orElse(new User());

        assertNotNull(userFound);
        assertEquals(expectedEmail, userFound.getContact().getEmail());
        assertEquals(expectedUser.getName(), userFound.getName());
        assertNotNull(userFound.getId());
    }
}