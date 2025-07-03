package com.petproject.term_paper.controller;

import com.petproject.term_paper.models.Deal;
import com.petproject.term_paper.service.DealService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deals")
@AllArgsConstructor
public class DealController {
    private final DealService dealService;

    @GetMapping("/{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable("id") Long id) {
        Deal foundDeal = dealService.getDealById(id);
        return ResponseEntity.ok(foundDeal);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Deal>> getAllDeals() {
        return ResponseEntity.ok(dealService.getAllDeals());
    }

    @PostMapping("/create")
    public ResponseEntity<Deal> createDeal(@RequestBody Deal deal) {
        Deal createdDeal = dealService.createDeal(deal);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDeal);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable("id") Long id) {
        dealService.deleteDeal(id);
        return ResponseEntity.noContent().build();
    }
}
