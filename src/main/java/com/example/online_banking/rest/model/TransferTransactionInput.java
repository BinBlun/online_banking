package com.example.online_banking.rest.model;

import lombok.Data;

@Data
public class TransferTransactionInput {

    private String accountNumber;
    private String amount;
    private String description;
}
