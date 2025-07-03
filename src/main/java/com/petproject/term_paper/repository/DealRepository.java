package com.petproject.term_paper.repository;

import com.petproject.term_paper.models.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
