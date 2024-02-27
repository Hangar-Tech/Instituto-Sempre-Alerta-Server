package com.institutosemprealerta.semprealerta.infrastructure.entity.user.mocks;

import com.institutosemprealerta.semprealerta.domain.model.UserDTO;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserMocks {
    public static User returnValidUserEntity() {
        User user = UserEntityFactory.INSTANCE.newUser();

        LocalDate birthDate = LocalDate.of(1990, 1, 1);

        user.setId(1L);
        user.setRegistration("123456");
        user.setName("John Doe");
        user.setBirthDate(birthDate);
        user.setGender("M");
        user.setContact(returnValidContact());
        user.setPassword("123456");
        user.setAddress(returnValidAddress());
        user.setRoles(UserRoles.USER);
        user.setCreatedAt(LocalDateTime.now());

        return user;
    }

    public static User returnValidUserToCreate() {
        User user = returnValidUserEntity();
        user.setId(null);
        return user;
    }

    public static User returnValidUserToUpdate() {
        User user = returnValidUserEntity();
        user.setRegistration("654321");
        return user;
    }

    public static Contact returnValidContact() {
       return ContactFactory.INSTANCE.newContact("user@email.com", "123456789");
    }

    public static Address returnValidAddress() {
        return new Address("Street", "123", "NY", "123546");
    }


    public static UserDTO returnValidUserDTO() {
        User user = returnValidUserEntity();
        return new UserDTO(
                user.getName(),
                user.getContact().getEmail(),
                user.getPassword(),
                user.getContact().getPhone(),
                user.getGender(),
                user.getBirthDate(),
                user.getRoles(),
                user.getAddress().getStreet(),
                user.getAddress().getNumber(),
                user.getAddress().getCity(),
                user.getAddress().getZipCode()
        );
    }
}
