package com.library_a3.library_a3.Controllers;

import com.library_a3.library_a3.domains.Credentials;
import com.library_a3.library_a3.repositories.CredentialsRepository;
import com.library_a3.library_a3.services.LoginService;
import com.library_a3.library_a3.shared.CreateCredentialsDTO;
import com.library_a3.library_a3.shared.dtos.LoginResponseDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.library_a3.library_a3.shared.enums.Role;


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

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody CreateCredentialsDTO body) throws Exception {
        UserDetails credentialsAlreadyExists = this.credentialsRepository.findByEmail(body.email);
        if(credentialsAlreadyExists != null) return ResponseEntity.unprocessableEntity().build();

        String hashedPassword = new BCryptPasswordEncoder().encode(body.pass);
        Credentials credentials = new Credentials(body.email, hashedPassword, Role.EMPLOYEE);

        this.credentialsRepository.save(credentials);

        return ResponseEntity.ok().build();
    }
}
