package com.library_a3.library_a3.Controllers;

import com.library_a3.library_a3.domains.Credentials;
import com.library_a3.library_a3.shared.CreateCredentialsDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credentials")

public class AuthController {

    @PostMapping()
    public Credentials create(@RequestBody CreateCredentialsDTO body) {
        return new Credentials(body.email, body.pass);
    }
}
