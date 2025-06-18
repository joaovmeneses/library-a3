package com.library_a3.library_a3.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "organization")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column
    String name;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    public Organization(String name){
        this.name = name;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
