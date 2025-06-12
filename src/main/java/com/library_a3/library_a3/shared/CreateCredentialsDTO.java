package com.library_a3.library_a3.shared;


import jakarta.validation.constraints.Email;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class CreateCredentialsDTO {
    @NotNull
    @Email(message = "Invalid e-mail")
    public String email;
    @NotNull
    public String pass;
}
