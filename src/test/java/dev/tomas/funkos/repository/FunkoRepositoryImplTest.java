package dev.tomas.funkos.repository;

import dev.tomas.funkos.models.Funko;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FunkoRepositoryImplTest {

    private FunkoRepositoryImpl funkoRepository;
    private Funko funko1;
    private Funko funko2;

    @BeforeEach
    void setUp() {
        funkoRepository = new FunkoRepositoryImpl();
        funko1 = new Funko(1L, UUID.randomUUID(), "Funko1", "Tipo1", 10.0, LocalDateTime.now(), LocalDateTime.now());
        funko2 = new Funko(2L, UUID.randomUUID(), "Funko2", "Tipo2", 20.0, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void save() {
        // Arrange
        Funko expectedFunko = funko1;

        // Act
        Funko actualFunko = funkoRepository.save(expectedFunko);

        // Assert
        assertNotNull(actualFunko);
        assertEquals(expectedFunko.getId(), actualFunko.getId());
        assertEquals(expectedFunko.getNombre(), actualFunko.getNombre());

        // Verify
        assertEquals(funko1, funkoRepository.findById(funko1.getId()));
    }

    @Test
    void findById() {
        // Arrange
        funkoRepository.save(funko1);

        // Act
        Funko actualFunko = funkoRepository.findById(funko1.getId());

        // Assert
        assertNotNull(actualFunko);
        assertEquals(funko1.getId(), actualFunko.getId());
        assertEquals(funko1.getNombre(), actualFunko.getNombre());

        // Verify
        assertEquals(funko1, actualFunko);
    }

    @Test
    void findByIdNotExist() {
        // Arrange
        UUID randomId = UUID.randomUUID();

        // Act
        Funko actualFunko = funkoRepository.findById(randomId);

        // Assert
        assertNull(actualFunko);

        // Verify
        assertNull(funkoRepository.findById(randomId));
    }

    @Test
    void findAll() {
        // Arrange
        funkoRepository.save(funko1);
        funkoRepository.save(funko2);
        List<Funko> expectedFunkos = Arrays.asList(funko1, funko2);

        // Act
        List<Funko> actualFunkos = funkoRepository.findAll();

        // Assert
        assertEquals(expectedFunkos, actualFunkos);
    }

    @Test
    void deleteByIdFunkoExists() {
        // Arrange
        funkoRepository.save(funko1);

        // Act
        Funko deletedFunko = funkoRepository.deleteById(funko1.getId());

        // Assert
        assertNotNull(deletedFunko);
        assertEquals(funko1.getId(), deletedFunko.getId());

        // Verify
        assertNull(funkoRepository.findById(funko1.getId()));
    }

    @Test
    void deleteByIdDoesNotExist() {
        // Arrange
        UUID randomId = UUID.randomUUID();

        // Act
        Funko deletedFunko = funkoRepository.deleteById(randomId);

        // Assert
        assertNull(deletedFunko);

        // Verify
        assertNull(funkoRepository.findById(randomId));
    }

    @Test
    void update() {
        // Arrange
        funkoRepository.save(funko1);
        Funko updatedFunko = new Funko(funko1.getNumero(), funko1.getId(), "Funko1Updated", "Tipo1Updated", 15.0, LocalDateTime.now(), LocalDateTime.now());

        // Act
        Funko actualFunko = funkoRepository.update(funko1.getId(), updatedFunko);

        // Assert
        assertNotNull(actualFunko);
        assertEquals(updatedFunko.getNombre(), actualFunko.getNombre());
        assertEquals(updatedFunko.getPrecio(), actualFunko.getPrecio());

        // Verify
        assertEquals(updatedFunko, funkoRepository.findById(funko1.getId()));
    }

    @Test
    void updateNotExist() {
        // Arrange
        Funko updatedFunko = new Funko(3L, UUID.randomUUID(), "FunkoNonExistent", "TipoNonExistent", 15.0, LocalDateTime.now(), LocalDateTime.now());
        UUID randomId = UUID.randomUUID();

        // Act
        Funko actualFunko = funkoRepository.update(randomId, updatedFunko);

        // Assert
        assertNull(actualFunko);

        // Verify
        assertNull(funkoRepository.findById(randomId));
    }
}
