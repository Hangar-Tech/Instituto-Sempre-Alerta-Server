package com.institutosemprealerta.semprealerta.infrastructure.entity.user;

public class ContactFactory {
    public static final ContactFactory INSTANCE = new ContactFactory();

    private ContactFactory() {
    }

    public Contact newContact() {
        return new Contact();
    }

    public Contact newContact(String email, String phone) {
        return new Contact(email, phone);
    }
}
