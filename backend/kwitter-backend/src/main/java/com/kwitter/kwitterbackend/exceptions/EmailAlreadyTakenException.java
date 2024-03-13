package com.kwitter.kwitterbackend.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException(String emailAlreadyTaken) {
        super("The email " + emailAlreadyTaken + " is already taken. Please try another email.");
    }
}
