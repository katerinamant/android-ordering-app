package com.example.softcafeengineer.domain;

public class Date {
    private int day;
    private int month;
    private int year;

    // Default constructor
    public Date() {
    }

    public Date(int day, int month, int year) throws InvalidDateException {
        try {
            this.setDay(day);
            this.setMonth(month);
            this.setYear(year);
        } catch (InvalidDateException e) {
            this.day = 0;
            this.month = 0;
            this.year = 0;
            throw new InvalidDateException(e + "\n Unable to set values.");
        }
    }

    public void setDay(int day) throws InvalidDateException {
        if (day < 1 || day > 31) {
            throw new InvalidDateException("Invalid day input.");
        } else {
            this.day = day;
        }
    }

    public int getDay() {
        return this.day;
    }

    public void setMonth(int month) throws InvalidDateException {
        if (month < 1 || month > 12) {
            throw new InvalidDateException("Invalid month input.");
        } else {
            this.month = month;
        }
    }

    public int getMonth() {
        return this.month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return this.year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (this.getClass() != obj.getClass()) return false;

        Date dateObj = (Date) obj;
        return (this.day == dateObj.getDay() && this.month == dateObj.getMonth() && this.year == dateObj.getYear());
    }

    @Override
    public String toString() {
        return String.format("%d - %d - %d", this.day, this.month, this.year);
    }
}
