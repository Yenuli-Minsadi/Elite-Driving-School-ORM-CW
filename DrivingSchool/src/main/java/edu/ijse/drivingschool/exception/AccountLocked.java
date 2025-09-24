package edu.ijse.drivingschool.exception;

public class AccountLocked extends RuntimeException {
    public AccountLocked(String message) {
        super(message);
    }
}
