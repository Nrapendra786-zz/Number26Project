package com.number26proj.enums;

/**
 * This is an error repository used to specify errors by its code and description
 * Created by NrapendraKumar on 06-03-2016.
 */
public enum ErrorRepository {

    TRANSACTION_ALREADY_EXIST(0, "Transaction Id Already Exist"),
    TRANSACTION_NOT_FOUND(1, "Transaction Id not found");

    private final int code;
    private final String description;

    private ErrorRepository(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
