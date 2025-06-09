package com.library_a3.library_a3.Controllers;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library_a3.library_a3.domains.Borrow;
import com.library_a3.library_a3.repositories.BorrowRepository;
import com.library_a3.library_a3.services.borrows.BorrowBookService;
import com.library_a3.library_a3.services.borrows.GetAllBorrowsService;
import com.library_a3.library_a3.services.borrows.ReturnBorrowService;
import com.library_a3.library_a3.shared.dtos.borrows.CreateBorrowDTO;

import jakarta.servlet.http.HttpServletRequest;

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

    @Autowired
    private ReturnBorrowService returnBorrowService;

    @PostMapping()
    public Borrow create(@NotNull @RequestBody CreateBorrowDTO body) {
        return this.borrowBookService.execute(body.getBookId(), body.getStudentId(), body.getDateToReturn());
    }

    @GetMapping()
    public List<Borrow> getAll() {
        String token = request.getHeader("Authorization");
        return this.getAllBorrowsService.execute(token.replace("Bearer ",""));
    }

    @PatchMapping("/{id}/return")
    public String returnBorrow(@PathVariable("id") String id){

        // return this.returnBorrowService.execute(id);
        return id;
        
    }
}