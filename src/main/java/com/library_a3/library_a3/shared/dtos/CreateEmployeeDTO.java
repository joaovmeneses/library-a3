package com.library_a3.library_a3.shared.dtos;

import com.library_a3.library_a3.shared.enums.employees.EmployeeCategoryEnum;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateEmployeeDTO {
    private String name;
    private EmployeeCategoryEnum category;
    private String email;
    private String pass;
}
