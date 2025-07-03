package com.petproject.term_paper.util;

import com.petproject.term_paper.models.*;
import com.petproject.term_paper.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EntityFinder {
    private final DealRepository dealRepository;
    private final EmployeeRepository employeeRepository;
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final ClientRepository clientRepository;

    public Employee findEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));
    }

    public Deal findDeal(Long dealId) {
        return dealRepository.findById(dealId)
                .orElseThrow(() -> new EntityNotFoundException("Deal not found with id: " + dealId));
    }

    public Client findClient(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + clientId));
    }

    public Property findProperty(Long propertyId) {
        return propertyRepository.findById(propertyId)
                .orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + propertyId));
    }

    public Owner findOwner(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with id: " + ownerId));
    }

//    public <T, ID> T findByIdOrThrow(JpaRepository<T, ID> repository, ID id, String entityName) {
//        return repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException(entityName + " not found with id: " + id));
//    }
//public Deal findDeal(Long dealId) {
//    return findByIdOrThrow(dealRepository, dealId, "Deal");
//}
// todo late
}
