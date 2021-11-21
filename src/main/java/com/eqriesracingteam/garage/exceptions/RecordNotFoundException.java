package com.eqriesracingteam.garage.exceptions;

public class RecordNotFoundException extends RuntimeException {
    // TODO: 21-11-2021 @SERIAL BOVEN TOEVOEGEN / VRAGEN
    //    @Serial
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}