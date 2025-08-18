package com.library_a3.library_a3.domains;

import java.util.Calendar;
import java.util.Date;

import com.library_a3.library_a3.shared.enums.reserve.ReservationStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name= "reserve")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserve {
    @Id @GeneratedValue(strategy= GenerationType.UUID)
    private String id;
    @Column(name="student_id", nullable=false)
    private String studentId;
    @Column(name="book_id", nullable=false)
    private String bookId;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private ReservationStatusEnum status;
    @Column(name="expires_at", nullable=false)
    private Date expiresAt;
    @Column(name="return_date", nullable=false)
    private Date returnDate;
    @Column(name="created_at", nullable=false)
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @Column(name="deleted_at")
    private Date deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    public Reserve(String studentId, String bookId){
        this.studentId = studentId;
        this.bookId = bookId;
        this.status = ReservationStatusEnum.OPEN;
        this.createdAt = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.createdAt);
        calendar.add(Calendar.HOUR, 72);
        this.expiresAt = calendar.getTime();

        Calendar returnCalendar = Calendar.getInstance();
        returnCalendar.setTime(this.createdAt);
        returnCalendar.add(Calendar.DAY_OF_MONTH, 7);
        this.returnDate = returnCalendar.getTime();
    }

}
