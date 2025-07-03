package com.petproject.term_paper.service;

import com.petproject.term_paper.models.Owner;
import com.petproject.term_paper.models.Property;
import com.petproject.term_paper.repository.OwnerRepository;
import com.petproject.term_paper.repository.PropertyRepository;
import com.petproject.term_paper.util.EntityFinder;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final PropertyRepository propertyRepository;
    private final EntityFinder entityFinder;

    public List<Owner> getAllOwners() {
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    public Owner getOwnerById(Long id) {
        return entityFinder.findOwner(id);
    }

    public Owner createOwner(Owner owner) {
        if (ownerRepository.existsByEmail(owner.getEmail())) {
            throw new IllegalArgumentException("Owner with this email already exists");
        }

        return ownerRepository.save(owner);
    }

    public void deleteOwner(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found"));

        for (Property property : owner.getProperties()) {
            property.setOwner(null);
            propertyRepository.save(property);
        }

        ownerRepository.deleteById(id);
    }

    public Property addPropertyToOwner(Long ownerId, Long propertyId) {
        Owner owner = entityFinder.findOwner(ownerId);
        Property property = entityFinder.findProperty(propertyId);

        List<Property> ownerProperties = owner.getProperties();
        ownerProperties.add(property);
        property.setOwner(owner);
        owner.setProperties(ownerProperties);

        ownerRepository.save(owner);
        return property;
    }

    public void deletePropertyFromOwner(Long ownerId, Long propertyId) {
        Owner owner = entityFinder.findOwner(ownerId);
        Property property = entityFinder.findProperty(propertyId);

        owner.getProperties().remove(property);
        property.setOwner(null); // Разрываем связь

        // Сохраняем изменения
        propertyRepository.save(property);
        ownerRepository.save(owner);
    }

}
