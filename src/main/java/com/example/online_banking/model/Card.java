package com.example.online_banking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Card")
@Data
public class Card {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String cardNumber;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "current_balance")
    private Long currentBalance;

    @Column(name = "active_date")
    private Date cartDate;
}

