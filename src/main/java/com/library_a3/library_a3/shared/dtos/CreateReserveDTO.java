package com.library_a3.library_a3.shared.dtos;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateReserveDTO {
    private String studentId;
    private String bookId;
}