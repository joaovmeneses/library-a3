package com.library_a3.library_a3.controllers;

import com.library_a3.library_a3.domains.Student;
import com.library_a3.library_a3.shared.dtos.CreateStudentDTO;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @PostMapping()
    public Student create(@RequestBody CreateStudentDTO body){
        return new Student(body.name, body.cpf, body.phone);
    }
}
