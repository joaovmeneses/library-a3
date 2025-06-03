package com.library_a3.library_a3.services;

import com.library_a3.library_a3.domains.Credentials;
import com.library_a3.library_a3.repositories.CredentialsRepository;
import com.library_a3.library_a3.shared.dtos.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO execute(Credentials credential) throws Exception {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        String token = this.tokenService.generateToken((Credentials) auth.getPrincipal());
        Credentials credentialFromDb = this.credentialsRepository.findByEmail(credential.getEmail());
        return new LoginResponseDTO(token, credentialFromDb.getRole());
    }
}
