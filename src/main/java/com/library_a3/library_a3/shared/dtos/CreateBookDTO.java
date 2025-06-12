package com.library_a3.library_a3.shared.dtos;

import com.library_a3.library_a3.shared.enums.BookCategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBookDTO {
    @NotBlank(message = "title is not optional")
    @Size(min = 1)
    public String title;
    @NotBlank(message = "author is not optional")
    @Size(min = 1)
    public String author;
    @NotNull(message = "category is not optional")
    public BookCategoryEnum category;
}
