package com.example.softcafeengineer.domain;

public class InvalidRevenueInputException extends Exception
{
    public InvalidRevenueInputException(String errormsg) {
        super(errormsg);
    }
}
