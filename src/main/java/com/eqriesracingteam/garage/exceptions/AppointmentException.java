package com.eqriesracingteam.garage.exceptions;

public class AppointmentException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public AppointmentException() {
        super();
    }

    public AppointmentException(String message) {
        super(message);
    }
}
