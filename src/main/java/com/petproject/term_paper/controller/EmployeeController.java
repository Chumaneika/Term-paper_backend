package com.petproject.term_paper.controller;

import com.petproject.term_paper.models.Deal;
import com.petproject.term_paper.models.Employee;
import com.petproject.term_paper.models.Owner;
import com.petproject.term_paper.service.EmployeeService;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getOwnerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-name")
    public ResponseEntity<Void> updateName(@RequestParam Long id, @RequestParam String name) {
        employeeService.updateName(id, name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-position")
    public ResponseEntity<Void> updatePosition(@RequestParam Long id, @RequestParam String position) {
        employeeService.updatePosition(id, position);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assign-deal/{employeeId}/{dealId}")
    public ResponseEntity<Deal> addDealToEmployee(@PathVariable("employeeId") Long employeeId, @PathVariable("dealId") Long dealId) {
        Deal addedDeal = employeeService.addDealToEmployee(employeeId, dealId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedDeal);

        // todo "come back late"
    }

    @DeleteMapping("/delete-property/{employeeId}/{dealId}")
    public ResponseEntity<Void> deletePropertyFromOwner(@PathVariable("employeeId") Long employeeId, @PathVariable("dealId") Long dealId) {
        employeeService.removeDealFromEmployee(employeeId, dealId);
        return ResponseEntity.noContent().build();

        // todo "come back late"
    }
}
