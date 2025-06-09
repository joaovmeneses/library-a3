package com.library_a3.library_a3.shared.dtos.borrows;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class CreateBorrowDTO {
    String bookId;
    String studentId;
    Date dateToReturn;
}
