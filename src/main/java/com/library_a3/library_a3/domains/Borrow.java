package com.library_a3.library_a3.domains;

import com.library_a3.library_a3.shared.enums.borrows.BorrowStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "borrow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "book_id", nullable = false, insertable = false, updatable = false)
    private String bookId;
    @Column(name = "student_id", nullable = false, insertable = false, updatable = false)
    private String studentId;
    @Column()
    private BorrowStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    public Borrow(String bookId, String studentId) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.status = BorrowStatusEnum.BORROWED;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
