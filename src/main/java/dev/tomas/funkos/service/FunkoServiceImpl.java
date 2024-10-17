package dev.tomas.funkos.service;

import dev.tomas.funkos.dto.FunkoDto;
import dev.tomas.funkos.exceptions.exception.UuidError;
import dev.tomas.funkos.mapper.FunkoMapper;
import dev.tomas.funkos.models.Funko;
import dev.tomas.funkos.repository.FunkoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = {"funkos"})
public class FunkoServiceImpl implements FunkoService {
    private final Logger logger = LoggerFactory.getLogger(FunkoServiceImpl.class);
    private final FunkoRepository funkoRepository;
    private final FunkoMapper funkoMapper;

    @Autowired
    public FunkoServiceImpl(FunkoRepository funkoRepository, FunkoMapper funkoMapper) {
        this.funkoRepository = funkoRepository;
        this.funkoMapper = funkoMapper;
    }

    @Override
    public Funko save(FunkoDto dto) {
        logger.info("Guardando nuevo Funko: {}", dto);
        UUID id = UUID.randomUUID();
        Funko nuevoFunko = funkoMapper.toFunko(id, dto);
        return funkoRepository.save(nuevoFunko);
    }

    @Override
    @Cacheable(key = "#id")
    public Funko findById(UUID id) {
        logger.info("Buscando Funko con id: {}", id);
        try {
            UUID.fromString(id.toString());
        } catch (IllegalArgumentException e) {
            throw new UuidError(id.toString());
        }
        return funkoRepository.findById(id);
    }

    @Override
    public List<Funko> findAll() {
        logger.info("Buscando todos los Funkos");
        return funkoRepository.findAll();
    }

    @Override
    @CacheEvict(key = "#id")
    public Funko deleteById(UUID id) {
        logger.info("Eliminando Funko con id: {}", id);
        return funkoRepository.deleteById(id);
    }
    @Override
    @CachePut(key = "#id")
    public Funko update(UUID id, FunkoDto dto) {
        logger.info("Actualizando Funko con id: {}", id);
        Funko funkoActual = this.findById(id);
        Funko funkoActualizado = funkoMapper.toFunko(dto, funkoActual);
        return funkoRepository.save(funkoActualizado);
    }
}