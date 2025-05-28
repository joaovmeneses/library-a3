package com.library_a3.library_a3.shared.enums;

public enum Role {
    EMPLOYEE("employee"),
    STUDENT("student");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
