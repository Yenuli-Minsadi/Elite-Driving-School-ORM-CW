package edu.ijse.drivingschool.exception;

public class MissingFields extends RuntimeException {
    public MissingFields(String message) {
        super(message);
    }
}
