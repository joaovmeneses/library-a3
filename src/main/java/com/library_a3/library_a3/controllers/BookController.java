package com.library_a3.library_a3.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.library_a3.library_a3.services.TokenService;
import com.library_a3.library_a3.shared.dtos.CreateBookDTO;

import jakarta.servlet.http.HttpServletRequest;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DeleteBookService deleteBookService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public Book create(@Validated @RequestBody CreateBookDTO body) {
        String token = request.getHeader("Authorization");
        String organizationId = tokenService.getOrganizationId(token.replace("Bearer ", "")).asString();
        Book book = new Book(body.title, body.author, body.category, organizationId);
        return this.bookRepository.save(book);
    }

    @GetMapping()
    public List<Book> getAll() {
        String token = request.getHeader("Authorization");
        return this.bookRepository.getAllBooksOrganization(tokenService.getOrganizationId(token.replace("Bearer ", "")).asString());
    }

    @GetMapping("/available")
    public List<Book> getAllAvailable() {
        return this.bookRepository.getAllAvailable();
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable("id") String id){
        return this.deleteBookService.execute(id);
    }
}