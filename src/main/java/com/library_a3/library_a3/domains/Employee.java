package com.library_a3.library_a3.domains;

import java.util.Date;

import com.library_a3.library_a3.shared.enums.employees.EmployeeCategoryEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column
    String name;
    @Column
    EmployeeCategoryEnum category;
    @Column(name = "credential_id")
    String credentialId;
    @Column(name = "organization_id")
    String organizationId;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    public Employee (String name, EmployeeCategoryEnum category, String credentialId,String organizationId) {
        this.category = category;
        this.name = name;
        this.credentialId = credentialId;
        this.organizationId = organizationId;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    public Employee (String name, EmployeeCategoryEnum category, String credentialId) {
        this.category = category;
        this.name = name;
        this.credentialId = credentialId;
        this.organizationId = "685aceff-56c7-474a-845c-a264350caf5d";
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}