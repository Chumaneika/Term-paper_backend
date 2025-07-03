package com.petproject.term_paper.service;

import com.petproject.term_paper.models.Client;
import com.petproject.term_paper.models.Deal;
import com.petproject.term_paper.repository.ClientRepository;
import com.petproject.term_paper.repository.DealRepository;
import com.petproject.term_paper.util.EntityFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final DealRepository dealRepository;
    private final EntityFinder entityFinder;

    public Client getClientById(Long id) {
        return entityFinder.findClient(id);
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    public Client createClient(Client client) {
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new IllegalArgumentException("Client with this email already exists");
        }
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Deal assignDealToClient(Long clientId, Long dealId) {
        Client foundClient = entityFinder.findClient(clientId);
        Deal foundDeal = entityFinder.findDeal(dealId);

        List<Deal> dealsOfClient = foundClient.getDeals();
        dealsOfClient.add(foundDeal);
        foundClient.setDeals(dealsOfClient);
        clientRepository.save(foundClient);

        foundDeal.setClient(foundClient);
        dealRepository.save(foundDeal);

        return foundDeal;
    }

    public void removeDealFromClient(Long clientId, Long dealId) {
        Client foundClient = entityFinder.findClient(clientId);
        Deal foundDeal = entityFinder.findDeal(dealId);

        List<Deal> dealsClient = foundClient.getDeals();
        dealsClient.remove(foundDeal);
        foundClient.setDeals(dealsClient);
        clientRepository.save(foundClient);

        foundDeal.setClient(null);
        dealRepository.save(foundDeal);
    }
}
