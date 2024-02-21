package com.institutosemprealerta.semprealerta.domain.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.Address;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.Contact;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.UserRoles;
import com.institutosemprealerta.semprealerta.utils.DateManipulation;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record UserDTO(
        @NotBlank
        String name,
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String phone,
        String gender,
        @PastOrPresent
        LocalDate birthDate,
        @NotNull
        UserRoles roles,
        String street,
        String number,
        String city,
        String zipCode

) {
    public User toDomain() {
        //LocalDate birth = DateManipulation.stringToLocalDate(birthDate);
        Contact contact = new Contact(
                email,
                phone
        );

        Address address = new Address(
                street,
                number,
                city,
                zipCode
        );
        return new User(
                name,
                password,
                gender,
                birthDate,
                roles,
                contact,
                address
        );
    }
}
