package com.library_a3.library_a3.services;


import com.library_a3.library_a3.domains.Credentials;
import com.library_a3.library_a3.repositories.CredentialsRepository;
import com.library_a3.library_a3.repositories.StudentRespository;
import com.library_a3.library_a3.shared.dtos.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCredentialService {
    @Autowired
    private CredentialsRepository credentialsRepository;

    public Credentials execute (String email, String pass){
        Credentials credentialByEmail = this.credentialsRepository.findByEmail(email);

        if(credentialByEmail != null){
            throw new RuntimeException("email already registered");
        }

        String hashedPassword = new BCryptPasswordEncoder().encode(pass);
        Credentials credential = new Credentials(email, hashedPassword);
        return this.credentialsRepository.save(credential);
    }
}
