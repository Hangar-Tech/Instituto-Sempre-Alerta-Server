package com.institutosemprealerta.semprealerta.domain.user;

public enum UserRoles {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
