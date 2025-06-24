package com.library_a3.library_a3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library_a3.library_a3.domains.Book;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findAllByDeletedAtIsNull();

    @Query(value = "SELECT * FROM book b WHERE b.status = 0 AND b.deleted_at IS NULL", nativeQuery = true)
    List<Book> getAllAvailable();

    @Query(value = "SELECT * FROM book b WHERE b.organization_id = :organizationId AND b.deleted_at IS NULL", nativeQuery= true)
    List<Book> getAllBooksOrganization(String organizationId);
}
