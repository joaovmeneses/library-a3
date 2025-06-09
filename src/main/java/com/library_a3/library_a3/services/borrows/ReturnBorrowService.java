package com.library_a3.library_a3.services.borrows;

import java.awt.print.Book;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library_a3.library_a3.domains.Borrow;
import com.library_a3.library_a3.repositories.BookRepository;
import com.library_a3.library_a3.repositories.BorrowRepository;
import com.library_a3.library_a3.shared.enums.BookStatusEnum;
import com.library_a3.library_a3.shared.enums.borrows.BorrowStatusEnum;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReturnBorrowService {
    
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BookRepository bookRepository;

    public Borrow execute(String borrowId){
        Optional<Borrow> optionalBorrow = this.borrowRepository.findByIdWithRelationship(borrowId);
        
        if(optionalBorrow == null){
            throw new EntityNotFoundException("Borrow not found");
        }

        Borrow borrow = optionalBorrow.get();

        if(borrow.getStatus() != BorrowStatusEnum.BORROWED){
            throw new EntityNotFoundException("Borrow not found");
        } 
        
        borrow.setStatus(BorrowStatusEnum.RETURNED);
        borrow.getBook().setStatus(BookStatusEnum.AVAILABLE);
        borrowRepository.save(borrow);
        // bookRepository.save(borrow.getBook());
        
        return borrow;
    }
}
