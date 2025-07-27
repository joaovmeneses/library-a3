package com.library_a3.library_a3.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library_a3.library_a3.domains.Reserve;
import com.library_a3.library_a3.services.TokenService;
import com.library_a3.library_a3.services.reserve.CreateReserveService;
import com.library_a3.library_a3.shared.dtos.CreateReserveDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reserve")

public class ReserveController {

    @Autowired
    private CreateReserveService createReserveService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping
    public ResponseEntity<Reserve> create(@Validated @RequestBody CreateReserveDTO body) {
        System.out.println("Body: " + body.toString());
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Reserve reserve = this.createReserveService.execute(body);
        return ResponseEntity.ok(reserve);
    }

}