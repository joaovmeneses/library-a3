package com.library_a3.library_a3.repositories;

import com.library_a3.library_a3.domains.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findAllByDeletedAtIsNull();

    @Query(value = "SELECT * FROM book b WHERE b.status = 0 AND b.deleted_at IS NULL", nativeQuery = true)
    List<Book> getAllAvailable();
}
