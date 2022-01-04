package com.eqriesracingteam.garage.exceptions;

public class InspectionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InspectionException() {
        super();
    }

    public InspectionException(String message) {
        super(message);
    }
}
