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
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final EntityFinder entityFinder;

    public List<Property> getAllProperties() {
        List<Property> properties = new ArrayList<>();
        propertyRepository.findAll().forEach(properties::add);
        return properties;
    }

    public Property getPropertyById(Long id) {
        return entityFinder.findProperty(id);
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public void deleteProperty(Long propertyId) {
        Property property = entityFinder.findProperty(propertyId);

        if (property.getOwner() == null) {
            propertyRepository.deleteById(propertyId);
        } else {
            Owner owner = ownerRepository.findById(property.getOwner().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Owner not found with id: " + property.getOwner().getId()));

            List<Property> propertiesOwner = owner.getProperties();
            propertiesOwner.remove(property);
            owner.setProperties(propertiesOwner);

            ownerRepository.save(owner);

            propertyRepository.deleteById(propertyId);

        }
    }

    public void assignForOwner(Long propertyId, Long ownerId) {
        Property property = entityFinder.findProperty(propertyId);
        Owner owner = entityFinder.findOwner(ownerId);

        List<Property> ownerProperties = owner.getProperties();
        ownerProperties.add(property);
        owner.setProperties(ownerProperties);
        property.setOwner(owner);

        ownerRepository.save(owner);
    }

    public void removeForOwner(Long propertyId, Long ownerId) {
        Property property = entityFinder.findProperty(propertyId);
        Owner owner = entityFinder.findOwner(ownerId);

        List<Property> propertiesOwner = owner.getProperties();
        propertiesOwner.remove(property);
        owner.setProperties(propertiesOwner);

        propertyRepository.save(property);
        ownerRepository.save(owner);
    }
}
