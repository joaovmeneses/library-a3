package com.library_a3.library_a3.services.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library_a3.library_a3.domains.Book;
import com.library_a3.library_a3.domains.Reserve;
import com.library_a3.library_a3.domains.Student;
import com.library_a3.library_a3.repositories.BookRepository;
import com.library_a3.library_a3.repositories.ReserveRepository;
import com.library_a3.library_a3.repositories.StudentRespository;
import com.library_a3.library_a3.shared.dtos.CreateReserveDTO;

@Service
public class CreateReserveService {

    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private StudentRespository studentRespository;
    @Autowired
    private BookRepository bookRepository;

    public Reserve execute(CreateReserveDTO data) {
        Student student = this.studentRespository.findById(data.getStudentId())
            .orElseThrow(() -> new RuntimeException("Student not found"));

        Book book = this.bookRepository.findById(data.getBookId())
            .orElseThrow(() -> new RuntimeException("Book not found"));

        Reserve reserve = new Reserve(student.getId(), book.getId());
        return this.reserveRepository.save(reserve);
    }
}
