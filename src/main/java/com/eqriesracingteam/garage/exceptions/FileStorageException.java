package com.eqriesracingteam.garage.exceptions;

import java.io.IOException;

public class FileStorageException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String msg;

    public FileStorageException(String msg, IOException ex) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
