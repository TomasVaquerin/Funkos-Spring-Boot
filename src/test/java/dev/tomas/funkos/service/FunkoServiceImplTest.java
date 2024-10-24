package dev.tomas.funkos.service;

import dev.tomas.funkos.dto.FunkoDto;
import dev.tomas.funkos.exceptions.exception.UuidError;
import dev.tomas.funkos.mapper.FunkoMapper;
import dev.tomas.funkos.models.Funko;
import dev.tomas.funkos.repository.FunkoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FunkoServiceImplTest {

    private final Funko funko1 = new Funko(1L, UUID.randomUUID(), "Funko1", "Tipo1", 10.0, LocalDateTime.now(), LocalDateTime.now());
    private final Funko funko2 = new Funko(2L, UUID.randomUUID(), "Funko2", "Tipo2", 20.0, LocalDateTime.now(), LocalDateTime.now());

    @Mock
    private FunkoRepository funkoRepository;
    @Mock
    private FunkoMapper funkoMapper;
    @InjectMocks
    private FunkoServiceImpl funkoService;
    @Captor
    private ArgumentCaptor<Funko> funkoCaptor;

    @Test
    void findAll() {
        List<Funko> expectedFunkos = Arrays.asList(funko1, funko2);
        when(funkoRepository.findAll()).thenReturn(expectedFunkos);

        List<Funko> actualFunkos = funkoService.findAll();

        assertIterableEquals(expectedFunkos, actualFunkos);
        verify(funkoRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        UUID id = funko1.getId();
        when(funkoRepository.findById(id)).thenReturn(funko1);

        Funko actualFunko = funkoService.findById(id);

        assertEquals(funko1, actualFunko);
        verify(funkoRepository, times(1)).findById(id);
    }


    @Test
    void save() {
        FunkoDto dto = new FunkoDto("Funko1", "Tipo1", 10.0);
        Funko expectedFunko = new Funko(1L, UUID.randomUUID(), "Funko1", "Tipo1", 10.0, LocalDateTime.now(), LocalDateTime.now());
        when(funkoMapper.toFunko(any(UUID.class), eq(dto))).thenReturn(expectedFunko);
        when(funkoRepository.save(any(Funko.class))).thenReturn(expectedFunko);

        Funko actualFunko = funkoService.save(dto);

        assertEquals(expectedFunko, actualFunko);
        verify(funkoRepository, times(1)).save(funkoCaptor.capture());
        verify(funkoMapper, times(1)).toFunko(any(UUID.class), eq(dto));
    }

    @Test
    void update() {
        UUID id = funko1.getId();
        FunkoDto dto = new FunkoDto("UpdatedFunko", "UpdatedTipo", 15.0);
        Funko updatedFunko = new Funko(1L, id, "UpdatedFunko", "UpdatedTipo", 15.0, LocalDateTime.now(), LocalDateTime.now());
        when(funkoRepository.findById(id)).thenReturn(funko1);
        when(funkoMapper.toFunko(dto, funko1)).thenReturn(updatedFunko);
        when(funkoRepository.save(updatedFunko)).thenReturn(updatedFunko);

        Funko actualFunko = funkoService.update(id, dto);

        assertEquals(updatedFunko, actualFunko);
        verify(funkoRepository, times(1)).findById(id);
        verify(funkoRepository, times(1)).save(funkoCaptor.capture());
        verify(funkoMapper, times(1)).toFunko(dto, funko1);
    }

    @Test
    void deleteById() {
        UUID id = funko1.getId();

        funkoService.deleteById(id);

        verify(funkoRepository, times(1)).deleteById(id);
    }
}