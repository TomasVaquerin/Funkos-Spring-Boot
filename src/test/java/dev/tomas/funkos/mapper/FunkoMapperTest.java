package dev.tomas.funkos.mapper;

import dev.tomas.funkos.dto.FunkoDto;
import dev.tomas.funkos.models.Funko;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FunkoMapperTest {

    // Inyectamos el mapper
    private final FunkoMapper funkoMapper = new FunkoMapper();

    @Test
    void toFunko_WithId() {
        // Arrange
        UUID id = UUID.randomUUID();
        FunkoDto funkoDto = new FunkoDto(
                "Spider-Man",
                "Pop! Vinyl",
                15.99
        );

        // Act
        Funko res = funkoMapper.toFunko(id, funkoDto);

        // Assert
        assertAll(
                () -> assertEquals(id, res.getId()),
                () -> assertEquals(funkoDto.getNombre(), res.getNombre()),
                () -> assertEquals(funkoDto.getTipo(), res.getTipo()),
                () -> assertEquals(funkoDto.getPrecio(), res.getPrecio()),
                () -> assertEquals(LocalDateTime.now().getDayOfMonth(), res.getCrateAt().getDayOfMonth()), // Validación simple de la fecha
                () -> assertEquals(LocalDateTime.now().getDayOfMonth(), res.getUpdateAt().getDayOfMonth())
        );
    }
}
