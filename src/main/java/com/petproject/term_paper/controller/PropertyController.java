package com.petproject.term_paper.controller;

import com.petproject.term_paper.models.Owner;
import com.petproject.term_paper.models.Property;
import com.petproject.term_paper.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/properties")
public class PropertyController {
    private final PropertyService propertyService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(propertyService.getPropertyById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property createdProperty = propertyService.createProperty(property);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable("id") Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}
