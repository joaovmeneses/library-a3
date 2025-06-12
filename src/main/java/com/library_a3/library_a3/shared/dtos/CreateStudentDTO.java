package com.library_a3.library_a3.shared.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateStudentDTO {
    @NotBlank(message = "name is not optional")
    @Size(min = 1)
    public String name;

    @NotBlank(message = "cpf is not optional")
    @Size(min = 14, max = 14)
    public String cpf;

    @NotBlank(message = "phone is not optional")
    @Size(min = 15, max = 15)
    public String phone;

    @NotBlank(message = "email is not optional")
    @Email(message = "invalid email")
    @Size(min = 7)
    public String email;

    @NotBlank(message = "email is not optional")
    @Size(min = 5)
    public String pass;
}
