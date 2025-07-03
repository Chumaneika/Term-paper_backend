package com.petproject.term_paper.controller;

import com.petproject.term_paper.models.Client;
import com.petproject.term_paper.models.Deal;
import com.petproject.term_paper.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        Client foundClient = clientService.getClientById(id);
        return ResponseEntity.ok(foundClient);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assign-deal/{client-id}/{deal-id}")
    public ResponseEntity<Deal> assignDealToClient(@PathVariable("client-id") Long clientId, @PathVariable("deal-id") Long dealId) {
        Deal addedDeal = clientService.assignDealToClient(clientId, dealId);
        return ResponseEntity.ok(addedDeal);
    }

    @DeleteMapping("/delete-deal/{client-id}/{deal-id}")
    public ResponseEntity<Void> removeDealFromClient(@PathVariable("client-id") Long clientId, @PathVariable("deal-id") Long dealId) {
        clientService.removeDealFromClient(clientId, dealId);
        return ResponseEntity.noContent().build();
    }

}
