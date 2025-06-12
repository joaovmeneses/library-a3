package com.library_a3.library_a3.shared.dtos;

import com.library_a3.library_a3.shared.enums.employees.EmployeeCategoryEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateEmployeeDTO {
    @NotBlank(message = "name is not optional")
    @Size(min = 1)
    private String name;
    @NotBlank(message = "category is not optional")
    @Size(min = 1)
    private EmployeeCategoryEnum category;
    @NotBlank(message = "email is not optional")
    @Email(message = "invalid email")
    @Size(min = 7)
    public String email;
    @NotBlank(message = "email is not optional")
    @Size(min = 5)
    public String pass;
}
