package com.petproject.term_paper.repository;

import com.petproject.term_paper.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
