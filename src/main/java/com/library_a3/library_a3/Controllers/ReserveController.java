package com.library_a3.library_a3.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library_a3.library_a3.domains.Reserve;
import com.library_a3.library_a3.repositories.ReserveRepository;
import com.library_a3.library_a3.services.TokenService;
import com.library_a3.library_a3.services.reserve.CreateReserveService;
import com.library_a3.library_a3.shared.dtos.CreateReserveDTO;

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
    @Autowired
    private ReserveRepository reserveRepository;

    @PostMapping
    public ResponseEntity<Reserve> create(@Validated @RequestBody CreateReserveDTO body) {

        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Reserve reserve = this.createReserveService.execute(body);
        return ResponseEntity.ok(reserve);
    }

    @GetMapping
    public List<Reserve> getAll() {
        return this.reserveRepository.findAllReserves();
    }
}
