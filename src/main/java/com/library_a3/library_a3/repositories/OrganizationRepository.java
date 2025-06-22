package com.library_a3.library_a3.repositories;

import com.library_a3.library_a3.domains.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization,String> {

}
