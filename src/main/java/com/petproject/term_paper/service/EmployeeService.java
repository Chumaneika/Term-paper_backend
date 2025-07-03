package com.petproject.term_paper.service;

import com.petproject.term_paper.models.Deal;
import com.petproject.term_paper.models.Employee;
import com.petproject.term_paper.repository.DealRepository;
import com.petproject.term_paper.repository.EmployeeRepository;
import com.petproject.term_paper.util.EntityFinder;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DealRepository dealRepository;
    private final EntityFinder entityFinder;

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    public Employee getEmployeeById(Long id) {
        return entityFinder.findEmployee(id);
    }

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByName(employee.getName())) {
            throw new IllegalArgumentException("Employee with this name already exists");
        }

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee foundEmployee = entityFinder.findEmployee(id);

        for (Deal deal : foundEmployee.getDeals()) {
            deal.setEmployee(null);
            dealRepository.save(deal);
        }

        employeeRepository.deleteById(id);
    }

    public void updateName(Long employeeId, String name) {
        Employee employee = entityFinder.findEmployee(employeeId);

        employee.setName(name);

        employeeRepository.save(employee);
    }

    public Employee updatePosition(Long employeeId, String position) {
        Employee employee = entityFinder.findEmployee(employeeId);

        employee.setPosition(position);

        return employeeRepository.save(employee);
    }

    public Deal addDealToEmployee(Long employeeId, Long dealId) {
        Employee employee = entityFinder.findEmployee(employeeId);
        Deal deal = entityFinder.findDeal(dealId);
        List<Deal> dealsOfEmployee = employee.getDeals();

        dealsOfEmployee.add(deal);
        employee.setDeals(dealsOfEmployee);
        deal.setEmployee(employee);

        employeeRepository.save(employee);

        return dealRepository.save(deal);
    }

    public Deal removeDealFromEmployee(Long employeeId, Long dealId) {
        Employee employee = entityFinder.findEmployee(employeeId);
        Deal deal = entityFinder.findDeal(dealId);
        List<Deal> dealsOfEmployee = employee.getDeals();

        dealsOfEmployee.remove(deal);
        employee.setDeals(dealsOfEmployee);
        deal.setEmployee(null);

        employeeRepository.save(employee);

        return dealRepository.save(deal);
    }
}
