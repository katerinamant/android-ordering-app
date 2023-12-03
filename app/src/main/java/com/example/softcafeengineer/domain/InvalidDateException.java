package com.example.softcafeengineer.domain;

public class InvalidDateException extends Exception
{
    public InvalidDateException(String errormsg) {
        super(errormsg);
    }
}
