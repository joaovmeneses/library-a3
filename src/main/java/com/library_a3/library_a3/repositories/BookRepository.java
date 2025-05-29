package com.library_a3.library_a3.repositories;

import com.library_a3.library_a3.domains.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {
}
