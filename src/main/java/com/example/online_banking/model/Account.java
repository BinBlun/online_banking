package com.example.online_banking.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Account")
@Data
public class Account {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "active_date")
    private Date activeDate;
}
