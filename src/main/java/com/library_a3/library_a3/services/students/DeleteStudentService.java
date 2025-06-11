package com.library_a3.library_a3.services.students;

import com.library_a3.library_a3.domains.Student;
import com.library_a3.library_a3.repositories.BorrowRepository;
import com.library_a3.library_a3.repositories.StudentRespository;
import com.library_a3.library_a3.shared.enums.borrows.BorrowStatusEnum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteStudentService {
    @Autowired
    private StudentRespository studentRespository;
    @Autowired
    private BorrowRepository borrowRepository;

    public Student execute(String studentId) {
        Optional<Student> stutendExists = this.studentRespository.findById(studentId);

        if(stutendExists.isEmpty()) {
            throw new EntityNotFoundException("Student not found");
        }

        Student student = stutendExists.get();

        Integer countStudentBoooksBorrowed = this.borrowRepository.countByStudentIdAndStatus(BorrowStatusEnum.BORROWED, student.getId());

        if(countStudentBoooksBorrowed > 0) {
            throw new RuntimeException("Have books to return");
        }

        student.delete();
        return this.studentRespository.save(student);
    }
}
