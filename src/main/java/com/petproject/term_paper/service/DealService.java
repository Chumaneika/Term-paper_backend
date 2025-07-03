package com.petproject.term_paper.service;

import com.petproject.term_paper.models.Client;
import com.petproject.term_paper.models.Deal;
import com.petproject.term_paper.models.Employee;
import com.petproject.term_paper.models.Property;
import com.petproject.term_paper.repository.ClientRepository;
import com.petproject.term_paper.repository.DealRepository;
import com.petproject.term_paper.repository.EmployeeRepository;
import com.petproject.term_paper.repository.PropertyRepository;
import com.petproject.term_paper.util.EntityFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DealService {
    private final DealRepository dealRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final PropertyRepository propertyRepository;
    private final EntityFinder entityFinder;

    public Deal getDealById(Long id) {
        return entityFinder.findDeal(id);
    }

    public List<Deal> getAllDeals() {
        List<Deal> deals = new ArrayList<>();
        dealRepository.findAll().forEach(deals::add);
        return deals;
    }

    public Deal createDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    public void deleteDeal(Long dealId) {
        Deal deal = entityFinder.findDeal(dealId);

        if (deal.getEmployee() != null) {
            Employee employee = deal.getEmployee();
            employee.getDeals().remove(deal);
            deal.setEmployee(null);
        }

        if (deal.getClient() != null) {
            Client client = deal.getClient();
            client.getDeals().remove(deal);
            deal.setClient(null);
        }

        if (deal.getProperty() != null) {
            Property property = deal.getProperty();
            property.getDeals().remove(deal);
            deal.setProperty(null);
        }

        dealRepository.save(deal);

        dealRepository.delete(deal);
    }
}
