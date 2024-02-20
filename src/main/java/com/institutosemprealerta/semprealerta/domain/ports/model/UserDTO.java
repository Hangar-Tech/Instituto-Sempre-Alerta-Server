package com.institutosemprealerta.semprealerta.domain.ports.model;


import com.institutosemprealerta.semprealerta.infrastructure.entity.user.UserRoles;

import java.time.LocalDate;

public record UserDTO(
        String name,
        String email,
        String password,
        String phone,
        String gender,
        LocalDate birthDate,
        UserRoles roles,
        String street,
        String number,
        String city,
        String zipCode

) {

}
