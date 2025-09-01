package com.library_a3.library_a3.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.library_a3.library_a3.domains.Reserve;
import com.library_a3.library_a3.repositories.ReserveRepository;
import com.library_a3.library_a3.services.TokenService;
import com.library_a3.library_a3.services.reserve.CreateReserveService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reserve")

public class ReserveController {

    @Autowired
    private CreateReserveService createReserveService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ReserveRepository reserveRepository;

    @PostMapping("/book/{bookId}")
    public Reserve create(@PathVariable String bookId) {

        String token = request.getHeader("Authorization").replace("Bearer ", "");
        return this.createReserveService.execute(bookId, token);
    }

    @GetMapping
    public List<Reserve> getAll() {
        String token = request.getHeader("Authorization");
        return this.reserveRepository.getAllReservesOrganization(tokenService.getOrganizationId(token.replace("Bearer ", "")).asString());
    }
}
