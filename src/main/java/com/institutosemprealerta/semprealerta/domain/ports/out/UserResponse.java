package com.institutosemprealerta.semprealerta.domain.ports.out;

import com.institutosemprealerta.semprealerta.infrastructure.entity.user.Address;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.Contact;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.UserRoles;
import jakarta.persistence.Embedded;

import java.time.LocalDate;

public record UserResponse(
         int registration,
         String name,

         String gender,
         LocalDate birthDate,

         UserRoles roles,

         Contact contact,
         Address address
) {

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getRegistration(),
                user.getName(),
                user.getGender(),
                user.getBirthDate(),
                user.getRoles(),
                user.getContact(),
                user.getAddress()
        );
    }
}
