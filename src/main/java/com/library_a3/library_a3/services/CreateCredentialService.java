package com.library_a3.library_a3.services;


import com.library_a3.library_a3.domains.Credentials;
import com.library_a3.library_a3.repositories.CredentialsRepository;
import com.library_a3.library_a3.repositories.StudentRespository;
import com.library_a3.library_a3.shared.dtos.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateCredentialService {
    @Autowired
    private CredentialsRepository credentialsRepository;

    public Credentials execute (String email, String pass){
        Credentials credentialByEmail = this.credentialsRepository.findByEmail(email);

        if(credentialByEmail != null){
            throw new RuntimeException("email already registered");
        }

        Credentials credential = new Credentials(email, pass);
        return this.credentialsRepository.save(credential);
    }
}


/*
Receber um email e passa e criar uma credencial no banco de dados

verificar se o email ja existe no banco;

injetar esse serviço no CreateStudentService
 */