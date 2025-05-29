package com.library_a3.library_a3.services.borrows;

import com.library_a3.library_a3.domains.Book;
import com.library_a3.library_a3.domains.Borrow;
import com.library_a3.library_a3.repositories.BookRepository;
import com.library_a3.library_a3.repositories.BorrowRepository;
import com.library_a3.library_a3.shared.enums.BookStatusEnum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowBookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowRepository borrowRepository;

    public Borrow execute(String bookId, String studentId) {
        Optional<Book> optionalBook = this.bookRepository.findById(bookId);

        if(optionalBook.isEmpty()) {
            throw new EntityNotFoundException("Book not found");
        }

        Book book = optionalBook.get();
        if(book.getStatus() != BookStatusEnum.AVAILABLE) {
            throw new RuntimeException("The book needs to have the status AVAILABLE");
        }

        Borrow borrow = this.borrowRepository.save(new Borrow(book.getId(), studentId));
        book.setBorrowed();
        this.bookRepository.save(book);
        return borrow;
    }
}
