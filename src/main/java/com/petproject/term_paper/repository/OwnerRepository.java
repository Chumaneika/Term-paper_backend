package com.petproject.term_paper.repository;

import com.petproject.term_paper.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existsByEmail(String email);
}
