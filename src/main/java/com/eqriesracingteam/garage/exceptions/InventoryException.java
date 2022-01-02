package com.eqriesracingteam.garage.exceptions;

public class InventoryException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InventoryException() {
        super();
    }

    public InventoryException(String message) {
        super(message);
    }
}
