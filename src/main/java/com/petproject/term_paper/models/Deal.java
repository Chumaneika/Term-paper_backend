package com.petproject.term_paper.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "deals")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "dateOpen", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOpen;

    @Column(name = "dateClose", nullable = true)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateClose;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusDeal status;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonBackReference(value = "owner-properties")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "client-deals")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference(value = "employee-deals")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(LocalDate dateOpen) {
        this.dateOpen = dateOpen;
    }

    public LocalDate getDateClose() {
        return dateClose;
    }

    public void setDateClose(LocalDate dateClose) {
        this.dateClose = dateClose;
    }

    public StatusDeal getStatus() {
        return status;
    }

    public void setStatus(StatusDeal status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

