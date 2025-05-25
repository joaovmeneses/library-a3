package com.library_a3.library_a3.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
public class Student {
    private String id;
    private String name;
    private String cpf;
    private String phone;
    private Date createdAt;
    private Date updatedAt;

    public Student(String name, String cpf, String phone){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.createdAt = new Date();
        this.updatedAt = new Date();

    }


}
