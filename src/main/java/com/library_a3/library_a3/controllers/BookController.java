package com.library_a3.library_a3.Controllers;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library_a3.library_a3.domains.Book;
import com.library_a3.library_a3.repositories.BookRepository;
import com.library_a3.library_a3.services.DeleteBookService;
import com.library_a3.library_a3.shared.dtos.CreateBookDTO;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DeleteBookService deleteBookService;

    @PostMapping()
    public Book create(@NotNull @RequestBody CreateBookDTO body) {
        Book book = new Book(body.title, body.author, body.category);
        return this.bookRepository.save(book);
    }

    @GetMapping()
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    @DeleteMapping("/{id}/delete")
    public Book deleteBook(@PathVariable("id") String id){
        return this.deleteBookService.execute(id);
    }
}