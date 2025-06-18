package com.library_a3.library_a3.shared.enums;

public enum Role {
    EMPLOYEE("EMPLOYEE"),
    STUDENT("STUDENT"),
    ADMIN("ADMIN");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
