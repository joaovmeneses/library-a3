package com.library_a3.library_a3.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library_a3.library_a3.domains.Credentials;
import com.library_a3.library_a3.domains.Organization;
import com.library_a3.library_a3.repositories.CredentialsRepository;
import com.library_a3.library_a3.services.LoginService;
import com.library_a3.library_a3.shared.CreateCredentialsDTO;
import com.library_a3.library_a3.shared.dtos.LoginResponseDTO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/credentials")
public class AuthController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private CredentialsRepository credentialsRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Validated  @RequestBody CreateCredentialsDTO body) throws Exception {
        LoginResponseDTO response = this.loginService.execute(new Credentials(body.email, body.pass));
        return ResponseEntity.ok(response);
    }
}
