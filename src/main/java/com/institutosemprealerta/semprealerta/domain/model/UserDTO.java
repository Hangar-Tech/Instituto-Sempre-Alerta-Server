package com.institutosemprealerta.semprealerta.domain.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.Address;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.Contact;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.UserRoles;
import com.institutosemprealerta.semprealerta.utils.DateManipulation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record UserDTO(
        String name,
        String email,
        String password,
        String phone,
        String gender,
        String birthDate,
        UserRoles roles,
        String street,
        String number,
        String city,
        String zipCode

) {
    public User toDomain() {
        LocalDate birth = DateManipulation.stringToLocalDate(birthDate);
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
                birth,
                roles,
                contact,
                address
        );
    }
}
