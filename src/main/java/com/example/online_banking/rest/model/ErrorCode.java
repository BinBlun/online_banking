package com.example.online_banking.rest.model;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {

    public static final String ACCOUNT_NOT_EXIST = "001";
    public static final String ACCOUNT_BALANCE_INVALID = "002";

    public static final Map<String, String> errorCodeMap = new HashMap<>();

    static {
        errorCodeMap.put(ACCOUNT_NOT_EXIST, "Account is not exist");
        errorCodeMap.put(ACCOUNT_BALANCE_INVALID, "Account balance is insufficient");
    }

    public static String getErrorMessage(String errorCode) {
        return errorCodeMap.get(errorCode);
    }
}
