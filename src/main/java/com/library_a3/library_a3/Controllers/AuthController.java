package com.library_a3.library_a3.Controllers;

import com.library_a3.library_a3.domains.Credentials;
import com.library_a3.library_a3.domains.Organization;
import com.library_a3.library_a3.repositories.CredentialsRepository;
import com.library_a3.library_a3.services.LoginService;
import com.library_a3.library_a3.shared.CreateCredentialsDTO;
import com.library_a3.library_a3.shared.dtos.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/credentials")
public class AuthController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private CredentialsRepository credentialsRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Validated  @RequestBody CreateCredentialsDTO body, Organization organization) throws Exception {
        LoginResponseDTO response = this.loginService.execute(new Credentials(body.email, body.pass), new Organization(organization.getId()));
        return ResponseEntity.ok(response);
    }
}
