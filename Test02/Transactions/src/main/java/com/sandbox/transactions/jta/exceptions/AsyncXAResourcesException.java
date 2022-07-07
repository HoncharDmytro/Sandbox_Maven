package com.sandbox.transactions.jta.exceptions;

public class AsyncXAResourcesException extends RuntimeException {

    public AsyncXAResourcesException(String message) {
        super(message);
    }
}