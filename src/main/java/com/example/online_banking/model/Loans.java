package com.example.online_banking.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loans")
@Data
public class Loans {
    @Id
    @Column(name = "LoanId")
    private Long id;

    @Column(name = "SSN")
    private Long SSN;

    @Column(name = "loansAmountTaken")
    private String loansAmountTaken;

    @Column(name = "loansAmountRepaid")
    private String loansAmountRepaid;
}
