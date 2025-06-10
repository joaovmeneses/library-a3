package com.library_a3.library_a3.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library_a3.library_a3.domains.Book;
import com.library_a3.library_a3.repositories.BookRepository;
import com.library_a3.library_a3.shared.enums.BookStatusEnum;
@Service
public class DeleteBookService{
    
    @Autowired
    private BookRepository bookRepository;

    public Book execute(String bookId){
        Optional<Book> optionalBook = this.bookRepository.findById(bookId);

        if(optionalBook == null){
            throw new Error("Book not found");
        }

        Book book = optionalBook.get();

        if(book.getStatus() != BookStatusEnum.AVAILABLE){
            throw new Error("The book is rented and needs to be returned");
        }

        
        book.deleteBook();
        bookRepository.save(book);

        return book;
    
    }
}


