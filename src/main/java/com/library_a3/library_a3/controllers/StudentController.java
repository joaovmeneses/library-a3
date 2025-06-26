package com.library_a3.library_a3.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library_a3.library_a3.domains.Student;
import com.library_a3.library_a3.repositories.StudentRespository;
import com.library_a3.library_a3.services.CreateStudentService;
import com.library_a3.library_a3.services.TokenService;
import com.library_a3.library_a3.services.students.DeleteStudentService;
import com.library_a3.library_a3.shared.dtos.CreateStudentDTO;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRespository studentRespository;
    @Autowired
    private CreateStudentService createStudentService;
    @Autowired
    private DeleteStudentService deleteStudentService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping()
    public List<Student> allStudents(){
        String token = request.getHeader("Authorization");
        return this.studentRespository.getAllStudentsOrganization(tokenService.getOrganizationId(token.replace("Bearer ", "")).asString());
    }

    @PostMapping()
    public ResponseEntity<Student> create(@Validated @RequestBody CreateStudentDTO body){
        String token = request.getHeader("Authorization");
        Student student = this.createStudentService.execute(body);
        return ResponseEntity.ok(student);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") String id) {
        Student student = this.deleteStudentService.execute(id);
        return ResponseEntity.ok(student);
    }
}
