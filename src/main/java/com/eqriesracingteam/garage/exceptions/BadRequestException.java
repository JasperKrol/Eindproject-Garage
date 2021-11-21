package com.eqriesracingteam.garage.exceptions;


public class BadRequestException extends RuntimeException {
    // TODO: 21-11-2021 @SERIAL BOVEN TOEVOEGEN / VRAGEN
    //    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
