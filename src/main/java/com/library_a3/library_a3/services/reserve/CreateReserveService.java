package com.library_a3.library_a3.services.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.Claim;
import com.library_a3.library_a3.domains.Book;
import com.library_a3.library_a3.domains.Reserve;
import com.library_a3.library_a3.domains.Student;
import com.library_a3.library_a3.repositories.BookRepository;
import com.library_a3.library_a3.repositories.ReserveRepository;
import com.library_a3.library_a3.repositories.StudentRespository;
import com.library_a3.library_a3.services.TokenService;
import com.library_a3.library_a3.shared.enums.BookStatusEnum;

@Service
public class CreateReserveService {

    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private StudentRespository studentRespository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TokenService tokenService;

    public Reserve execute(String bookId, String token) {

        Claim studentId = this.tokenService.getUserId(token);
        
        Student student = this.studentRespository.findById(studentId.asString())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));


        book.setStatus(BookStatusEnum.RESERVED);

        Reserve reserve = new Reserve(student.getId(), book.getId());
        this.reserveRepository.save(reserve);
        return reserve;
    }
}
