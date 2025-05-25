package com.library_a3.library_a3.controllers;

import com.library_a3.library_a3.domains.Book;
import com.library_a3.library_a3.shared.dtos.CreateBookDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @PostMapping()
    public Book create(@RequestBody CreateBookDTO body) {
        return new Book(body.title, body.author, body.category);
    }
}