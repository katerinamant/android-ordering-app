package com.example.softcafeengineer.domain;

public class InvalidStatusException extends Exception
{
    public InvalidStatusException(String errormsg) {
        super(errormsg);
    }
}
