package com.example.actra.csv;

public class DigesterException extends Exception {

    public DigesterException() {
    }

    public DigesterException(String message) {
        super(message);
    }

    public DigesterException(String message, Throwable cause) {
        super(message, cause);
    }

    public DigesterException(Throwable cause) {
        super(cause);
    }
}
