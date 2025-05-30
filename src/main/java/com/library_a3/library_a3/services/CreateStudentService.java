package com.library_a3.library_a3.services;

import com.library_a3.library_a3.domains.Credentials;
import com.library_a3.library_a3.domains.Student;
import com.library_a3.library_a3.repositories.StudentRespository;
import com.library_a3.library_a3.shared.dtos.CreateStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateStudentService {

    @Autowired
    private StudentRespository studentRespository;
    @Autowired private CreateCredentialService createCredentialService;

    public Student execute(CreateStudentDTO data){
        Student studentByCpf = this.studentRespository.findByCpf(data.cpf);
        if(studentByCpf != null){
            throw new RuntimeException("cpf already in use");
        }

        Student studentByPhone = this.studentRespository.findByPhone(data.phone);
        if(studentByPhone != null){
            throw new RuntimeException("phone already in use");
        }

        Credentials credential = this.createCredentialService.execute(data.email, data.pass);
        Student student = new Student(data.name, data.cpf, data.phone, "credential.");
        return this.studentRespository.save(student);
    }
}
