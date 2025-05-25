package com.library_a3.library_a3.domains;

import com.library_a3.library_a3.shared.enums.BookCategoryEnum;
import com.library_a3.library_a3.shared.enums.BookStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Book {
    private String id;
    private String title;
    private String author;
    private BookCategoryEnum category;
    private BookStatusEnum status;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Book(String title, String author, BookCategoryEnum category){
        this.id = UUID.randomUUID().toString();
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


}
