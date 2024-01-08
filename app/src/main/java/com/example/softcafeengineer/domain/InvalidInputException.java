package com.example.softcafeengineer.domain;

public class InvalidInputException extends Exception {
    public InvalidInputException(String errormsg) {
        super(errormsg);
    }
}
