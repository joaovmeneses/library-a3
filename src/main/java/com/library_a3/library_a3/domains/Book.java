package com.library_a3.library_a3.domains;

import java.util.Date;

import com.library_a3.library_a3.shared.enums.BookCategoryEnum;
import com.library_a3.library_a3.shared.enums.BookStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private BookCategoryEnum category;
    @Column
    private BookStatusEnum status;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    public Book(String title, String author, BookCategoryEnum category){
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = BookStatusEnum.AVAILABLE;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public void setBorrowed(){
        if(this.status != BookStatusEnum.AVAILABLE) {
            System.out.println("Status need be AVAILABLE");
            return;
        }
        this.status = BookStatusEnum.BORROWED;
    }

    public void setAvailable(){
        if(this.status != BookStatusEnum.BORROWED) {
            System.out.println("Status need be BORROWED");
            return;
        }
        this.status = BookStatusEnum.AVAILABLE;
    }

    public void deleteBook(){
        this.deletedAt = new Date();
    }

}
