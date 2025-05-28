package com.library_a3.library_a3.domains;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, String> {
    Credentials findByEmail(String email);
}
