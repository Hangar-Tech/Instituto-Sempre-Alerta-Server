package com.institutosemprealerta.semprealerta.infrastructure.entity.user;


import com.institutosemprealerta.semprealerta.domain.model.UserDTO;
import com.institutosemprealerta.semprealerta.utils.DateManipulation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@DynamicUpdate
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private int registration;
    private String name;
    private String password;
    private String gender;
    private LocalDate birthDate;

    private UserRoles roles;
    @Embedded
    private Contact contact;

    @Embedded
    private Address address;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public User() {
        this.registration = generateRegistration();
    }

    public User(String name, String password, String gender, LocalDate birthDate, UserRoles roles, Contact contact, Address address) {
        this.registration = this.generateRegistration();
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.roles = roles;
        this.contact = contact;
        this.address = address;
    }

    public User fromModelToDomain(UserDTO dto) {
        LocalDate birth = DateManipulation.stringToLocalDate(dto.birthDate());
        return new User(
                dto.name(),
                dto.password(),
                dto.gender(),
                birth,
                dto.roles(),
                new Contact(dto.email(), dto.phone()),
                new Address(dto.street(), dto.number(), dto.city(), dto.zipCode())
        );
    }


    private int generateRegistration() {
        Set<Integer> registrations = new HashSet<>(8);

        while (registrations.size() < 8) {
            for (int i = 0; i < 8; i++) {
                int digit = (int) (Math.random() * 9) + 1;
                registrations.add(digit);
            }
        }
        StringBuilder builder = new StringBuilder();
        registrations.forEach(builder::append);
        return Integer.parseInt(builder.toString());
    }
}
