package dev.tomas.funkos.exceptions;

public abstract class Exception extends RuntimeException {
    public Exception(String message) {
        super(message);
    }
}