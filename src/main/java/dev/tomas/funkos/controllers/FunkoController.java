package dev.tomas.funkos.controllers;

import dev.tomas.common.Controller;
import dev.tomas.funkos.dto.FunkoDto;
import dev.tomas.funkos.models.Funko;
import dev.tomas.funkos.service.FunkoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/funkos")
public class FunkoController implements Controller<Funko, UUID> {
    private static final String FUNKO_NOT_FOUND = "Funko con id: {} no encontrado";
    private final FunkoService service;
    private final Logger logger = LoggerFactory.getLogger(FunkoController.class);

    @Autowired
    public FunkoController(FunkoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Funko>> getAll() {
        logger.info("Obteniendo todos los Funkos");
        List<Funko> funkos = service.findAll();
        return ResponseEntity.ok(funkos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Funko> getById(@PathVariable UUID id) {
        logger.info("Obteniendo Funko con id: {}", id);
        Funko funko = service.findById(id);
        if (funko != null) {
            return ResponseEntity.ok(funko);
        } else {
            logger.warn(FUNKO_NOT_FOUND, id);
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Funko> create(@Valid @RequestBody FunkoDto dto) {
        logger.info("Creando nuevo Funko: {}", dto);
        Funko savedFunko = service.save(dto);
        return ResponseEntity.ok(savedFunko);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Funko> update(@PathVariable  UUID id, @RequestBody  @Valid FunkoDto dto) {
        logger.info("Actualizando Funko con id: {}", id);
        if (service.findById(id) == null) {
            logger.warn(FUNKO_NOT_FOUND, id);
            return ResponseEntity.notFound().build();
        }
        Funko updatedFunko = service.update(id, dto);
        return ResponseEntity.ok(updatedFunko);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        logger.info("Eliminando Funko con id: {}", id);
        Funko funko = service.deleteById(id);
        if (funko != null) {
            return ResponseEntity.noContent().build();
        } else {
            logger.warn(FUNKO_NOT_FOUND, id);
            return ResponseEntity.notFound().build();
        }
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}