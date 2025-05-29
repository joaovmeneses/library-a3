package com.library_a3.library_a3.shared.dtos;

import com.library_a3.library_a3.shared.enums.BookCategoryEnum;
import lombok.Data;

@Data
public class CreateBookDTO {
    public String title;
    public String author;
    public BookCategoryEnum category;
}
