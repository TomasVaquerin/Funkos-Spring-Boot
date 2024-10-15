package dev.tomas.funkos.controllers;

import dev.tomas.common.Controller;
import dev.tomas.funkos.dto.FunkoDto;
import dev.tomas.funkos.mapper.FunkoMapper;
import dev.tomas.funkos.models.Funko;
import dev.tomas.funkos.repository.FunkoRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/funkos")
public class FunkoController implements Controller<Funko, UUID> {
    private static final String FUNKO_NOT_FOUND = "Funko con id: {} no encontrado";

    private final FunkoRepositoryImpl repository;
    private final FunkoMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(FunkoController.class);

    @Autowired
    public FunkoController(FunkoRepositoryImpl repository, FunkoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Funko>> getAll() {
        logger.info("Obteniendo todos los Funkos");
        List<Funko> funkos = repository.findAll();
        return ResponseEntity.ok(funkos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Funko> getById(@PathVariable UUID id) {
        logger.info("Obteniendo Funko con id: {}", id);
        Funko funko = repository.findById(id);
        if (funko != null) {
            return ResponseEntity.ok(funko);
        } else {
            logger.warn(FUNKO_NOT_FOUND, id);
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Funko> create(@RequestBody FunkoDto funkoDto) {
        logger.info("Creando nuevo Funko: {}", funkoDto);
        Funko funko = mapper.toFunko(funkoDto);
        Funko savedFunko = repository.save(funko);
        return ResponseEntity.ok(savedFunko);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Funko> update(@PathVariable UUID id, @RequestBody FunkoDto funkoDto) {
        logger.info("Actualizando Funko con id: {}", id);
        if (repository.findById(id) == null) {
            logger.warn(FUNKO_NOT_FOUND, id);
            return ResponseEntity.notFound().build();
        }
        Funko funko = mapper.toFunko(funkoDto);
        funko.setId(id);
        Funko updatedFunko = repository.save(funko);
        return ResponseEntity.ok(updatedFunko);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        logger.info("Eliminando Funko con id: {}", id);
        Funko funko = repository.deleteById(id);
        if (funko != null) {
            return ResponseEntity.noContent().build();
        } else {
            logger.warn(FUNKO_NOT_FOUND, id);
            return ResponseEntity.notFound().build();
        }
    }
}