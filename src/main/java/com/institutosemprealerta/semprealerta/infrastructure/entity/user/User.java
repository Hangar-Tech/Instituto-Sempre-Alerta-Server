package com.institutosemprealerta.semprealerta.infrastructure.entity.user;


import com.institutosemprealerta.semprealerta.domain.model.UserDTO;
import com.institutosemprealerta.semprealerta.utils.DateManipulation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Random;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String registration;
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
    }


    public User(String name, String password, String gender, LocalDate birthDate, UserRoles roles, Contact contact, Address address) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.roles = roles;
        this.contact = contact;
        this.address = address;
    }


    @PrePersist
    public void generateRegistration() {
        String prefix = this.name.substring(0, 3).toLowerCase() + "-";
        this.registration = generateRegistration(prefix);
    }

    private String generateRegistration(String prefix) {
        StringBuilder registration = new StringBuilder(prefix);
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10);
            registration.append(digit);
        }

        return registration.toString();
    }

    public User fromModelToDomain(UserDTO dto) {
        //LocalDate birth = DateManipulation.stringToLocalDate(dto.birthDate());
        return new User(
                dto.name(),
                dto.password(),
                dto.gender(),
                dto.birthDate(),
                dto.roles(),
                new Contact(dto.email(), dto.phone()),
                new Address(dto.street(), dto.number(), dto.city(), dto.zipCode())
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roles == UserRoles.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return this.contact.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
