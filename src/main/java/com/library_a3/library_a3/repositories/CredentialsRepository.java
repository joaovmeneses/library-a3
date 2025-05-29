package com.library_a3.library_a3.repositories;

import com.library_a3.library_a3.domains.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, String> {
    Credentials findByEmail(String email);
}
