package com.library_a3.library_a3.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter

public class Credentials {
    private String id;
    private String email;
    private String pass;

    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Credentials(String email, String pass) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.pass = pass;

        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.deletedAt = new Date();
    }
}