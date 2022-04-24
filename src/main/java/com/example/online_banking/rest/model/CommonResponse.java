package com.example.online_banking.rest.model;

import lombok.Data;

@Data
public class CommonResponse {
    private String status;
    private String errorCode;
    private String errorDesc;
    private Object data;
}
