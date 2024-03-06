package com.institutosemprealerta.semprealerta.infrastructure.repositories;

import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.UserEntityFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for JpaUserRepository")
class JpaUserRepositoryTest {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @BeforeEach
    void setUp() {
        User userToCreate = UserEntityFactory.INSTANCE.newUser(
                "Teste",
                "123456",
                "M",
                LocalDate.now(),
                null,
                "user@email.com",
                "123456",
                null
        );
        this.jpaUserRepository.save(userToCreate);
    }

    @AfterEach
    void tearDown() {
        this.jpaUserRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        Optional<User> userFound = this.jpaUserRepository.findByEmail("user@email.com");
        LocalDate now = LocalDate.now();

        assertTrue(userFound.isPresent());
        assertNotNull(userFound.get().getId());
        assertEquals("Teste", userFound.get().getName());
        assertEquals("123456", userFound.get().getPassword());
        assertEquals("M", userFound.get().getGender());
        assertEquals(now, userFound.get().getBirthDate());

    }

    @Test
    void findByRegistration() {
        Optional<User> userFound = this.jpaUserRepository.findByEmail("user@email.com");

        userFound.ifPresent(user -> {
            Optional<User> userByRegistration = this.jpaUserRepository.findByRegistration(user.getRegistration());
            assertTrue(userByRegistration.isPresent());
            assertEquals(user, userByRegistration.get());
            assertNotNull(userByRegistration.get().getId());
        });
    }
}