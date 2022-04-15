package com.example.online_banking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Account")
@Data
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "current_balance")
    private Long currentBalance;

    @Column(name = "active_date")
    private Date activeDate;
}
