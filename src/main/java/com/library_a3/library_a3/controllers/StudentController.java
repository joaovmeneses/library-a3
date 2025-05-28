package com.library_a3.library_a3.Controllers;

import com.library_a3.library_a3.domains.Student;
import com.library_a3.library_a3.repositories.StudentRespository;
import com.library_a3.library_a3.shared.dtos.CreateStudentDTO;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRespository studentRespository;

    @GetMapping()
    public List<Student> allStudents(){
        return this.studentRespository.findAll();
    }

    @PostMapping()
    public Student create(@RequestBody CreateStudentDTO body){
        return new Student(body.name, body.cpf, body.phone);
    }
}
