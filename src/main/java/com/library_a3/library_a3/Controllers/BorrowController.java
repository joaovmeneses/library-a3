package com.library_a3.library_a3.Controllers;

import com.library_a3.library_a3.domains.Borrow;
import com.library_a3.library_a3.repositories.BorrowRepository;
import com.library_a3.library_a3.services.borrows.BorrowBookService;
import com.library_a3.library_a3.services.borrows.GetAllBorrowsService;
import com.library_a3.library_a3.shared.dtos.borrows.CreateBorrowDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/borrows")
public class BorrowController {

    @Autowired
    private BorrowBookService borrowBookService;

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private GetAllBorrowsService getAllBorrowsService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping()
    public Borrow create(@NotNull @RequestBody CreateBorrowDTO body) {
        return this.borrowBookService.execute(body.getBookId(), body.getStudentId());
    }

    @GetMapping()
    public List<Borrow> getAll() {
        String token = request.getHeader("Authorization");
        return this.getAllBorrowsService.execute(token.replace("Bearer ",""));
    }
}