package com.institutosemprealerta.semprealerta.domain.user;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@DynamicUpdate
@Getter
@Setter
public class User {
    @Id
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

    private int generateRegistration() {
        Set<Integer> registrations = new HashSet<>(8);

        while (registrations.size() < 8) {
            for (int i = 0; i < 8; i++) {
                registrations.add((int) (Math.random() * 10));
            }
        }
        return Integer.parseInt(registrations.toString());
    }
}
