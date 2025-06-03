package com.library_a3.library_a3.Controllers;

import com.library_a3.library_a3.domains.Book;
import com.library_a3.library_a3.repositories.BookRepository;
import com.library_a3.library_a3.shared.dtos.CreateBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping()
    public Book create(@RequestBody CreateBookDTO body) {
        Book book = new Book(body.title, body.author, body.category);
        return this.bookRepository.save(book);
    }

    @GetMapping()
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }
}