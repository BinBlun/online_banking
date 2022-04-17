package com.example.online_banking.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "account_number")
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @Column(name = "active_date")
    private Date activeDate;
}
