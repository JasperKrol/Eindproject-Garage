package com.eqriesracingteam.garage.exceptions;

public class UserNotFoundException extends RuntimeException {
    // TODO: 21-11-2021 @SERIAL BOVEN TOEVOEGEN / VRAGEN
    //    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
