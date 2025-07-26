package com.library_a3.library_a3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library_a3.library_a3.domains.Reserve;

public interface ReserveRepository extends  JpaRepository <Reserve, String> {

}
