package com.example.online_banking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionID;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountId;

    @Column(name = "transaction_amount", nullable = false)
    @Min(value = 0)
    private Long transactionAmount;

    @Column(name = "transaction_date", updatable = false)
    @CreationTimestamp
    private Date transactionDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "recipient_account_id", nullable = false)
    private Long recipientAccountID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
