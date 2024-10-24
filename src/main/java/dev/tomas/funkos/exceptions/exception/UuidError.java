package dev.tomas.funkos.exceptions.exception;

import dev.tomas.funkos.exceptions.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UuidError extends Exception {
    public UuidError(String uuid) {
        super("UUID: " + uuid + " no válido o de formato incorrecto");
    }
}