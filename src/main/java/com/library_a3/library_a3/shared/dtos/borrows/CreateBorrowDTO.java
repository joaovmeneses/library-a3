package com.library_a3.library_a3.shared.dtos.borrows;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class CreateBorrowDTO {
    @NotBlank(message = "bookId is not optional")
    @Size(min = 36, max = 36)
    String bookId;
    @NotBlank(message = "studentId is not optional")
    @Size(min = 36, max = 36)
    String studentId;
    @NotNull(message = "dateToReturn is not optional")
    @DateTimeFormat
    Date dateToReturn;
}
