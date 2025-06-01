package com.library_a3.library_a3.Controllers;

import com.library_a3.library_a3.domains.Credentials;
import com.library_a3.library_a3.repositories.CredentialsRepository;
import com.library_a3.library_a3.services.LoginService;
import com.library_a3.library_a3.shared.CreateCredentialsDTO;
import com.library_a3.library_a3.shared.dtos.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity login(@RequestBody CreateCredentialsDTO body) throws Exception {
        String token = this.loginService.execute(new Credentials(body.email, body.pass));
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
