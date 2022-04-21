package com.example.online_banking.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bank")
@Data
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BankId", nullable = false)
    private Long id;

    @Column(name = "bankName")
    private String bankName;

    @Column(name = "bankMoney")
    private Long bankMoney;
}
