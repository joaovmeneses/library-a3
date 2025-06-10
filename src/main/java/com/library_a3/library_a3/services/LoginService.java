package com.library_a3.library_a3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.library_a3.library_a3.domains.Credentials;
import com.library_a3.library_a3.domains.Employee;
import com.library_a3.library_a3.domains.Student;
import com.library_a3.library_a3.repositories.CredentialsRepository;
import com.library_a3.library_a3.repositories.EmployeeRepository;
import com.library_a3.library_a3.repositories.StudentRespository;
import com.library_a3.library_a3.shared.dtos.LoginResponseDTO;
import com.library_a3.library_a3.shared.enums.Role;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LoginService {
    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private StudentRespository studentRespository;

    public LoginResponseDTO execute(Credentials data) throws Exception {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        Credentials credentialFromDb = this.credentialsRepository.findByEmail(data.getEmail());

        if(credentialFromDb.getRole().equals(Role.EMPLOYEE)) {
            Optional<Employee> employee = this.employeeRepository.getByCredentialId(credentialFromDb.getId());
            if(employee.isEmpty()) {
                throw new EntityNotFoundException("Not found employee");
            }
            String token = this.tokenService.generateToken((Credentials) auth.getPrincipal(), employee.get().getId());
            return new LoginResponseDTO(token, credentialFromDb.getRole());
        }

        Optional<Student> student = this.studentRespository.getByCredentialId(credentialFromDb.getId());
        if(student.isEmpty()) {
            throw new EntityNotFoundException("Not found student");
        }
        System.out.println("student.get()");
        System.out.println(student.get());
        String token = this.tokenService.generateToken((Credentials) auth.getPrincipal(), student.get().getId());
        return new LoginResponseDTO(token, credentialFromDb.getRole());

    }
}
