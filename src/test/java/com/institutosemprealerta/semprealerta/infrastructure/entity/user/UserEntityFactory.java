package com.institutosemprealerta.semprealerta.infrastructure.entity.user;

import java.time.LocalDate;

public class UserEntityFactory {
    public static final UserEntityFactory INSTANCE = new UserEntityFactory();

    private UserEntityFactory() {
    }

    public User newUser() {
        return new User();
    }

    public User newUser(
            String name,
            String password,
            String gender,
            LocalDate birthday,
            UserRoles userRoles,
            String email,
            String phone,
            Address address
    ) {
        Contact contact = ContactFactory.INSTANCE.newContact(email, phone);

        return new User(name, password, gender, birthday, userRoles, contact, address);
    }


}
