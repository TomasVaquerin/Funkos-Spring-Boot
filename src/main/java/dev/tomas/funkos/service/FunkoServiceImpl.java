package dev.tomas.funkos.service;

import dev.tomas.funkos.dto.FunkoDto;
import dev.tomas.funkos.mapper.FunkoMapper;
import dev.tomas.funkos.models.Funko;
import dev.tomas.funkos.repository.FunkoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
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
        Funko funko = funkoMapper.toFunko(dto);
        return funkoRepository.save(funko);
    }

    @Override
    public Funko findById(UUID id) {
        logger.info("Buscando Funko con id: {}", id);
        return funkoRepository.findById(id);
    }

    @Override
    public List<Funko> findAll() {
        logger.info("Buscando todos los Funkos");
        return funkoRepository.findAll();
    }

    @Override
    public Funko deleteById(UUID id) {
        logger.info("Eliminando Funko con id: {}", id);
        return funkoRepository.deleteById(id);
    }
}