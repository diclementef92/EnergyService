package com.epic_energies.business.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String businessName;
    private Long vatNumber;
    private String email;
    private LocalDate insertData;
    private LocalDate lastContactData;
    private Integer annualIncome;
    private String pec;
    private Long phoneNumber;
    private String contactEmail;
    private String contactName;
    private Long contactPhone;

    @Enumerated(EnumType.STRING)
    private E_CustomerType costumerType;

    @OneToOne
    private Address legalAddress;

    @OneToOne
    private Address operativeAddress;

    @OneToMany(mappedBy = "customer")
    private List<Invoice> list_invoices;

}
