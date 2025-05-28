package com.library_a3.library_a3.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Entity(name="student")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column
    private String name;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String phone;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    public Student(String name, String cpf, String phone){
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.createdAt = new Date();
        this.updatedAt = new Date();

    }


}
