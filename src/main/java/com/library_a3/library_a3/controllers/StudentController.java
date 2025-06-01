package com.library_a3.library_a3.Controllers;

import com.library_a3.library_a3.domains.Student;
import com.library_a3.library_a3.repositories.StudentRespository;
import com.library_a3.library_a3.services.CreateStudentService;
import com.library_a3.library_a3.shared.dtos.CreateStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRespository studentRespository;
    @Autowired
    private CreateStudentService createStudentService;

    @GetMapping()
    public List<Student> allStudents(){
        return this.studentRespository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Student> create(@RequestBody CreateStudentDTO body){
        Student student = this.createStudentService.execute(body);
        return ResponseEntity.ok(student);
    }
}
