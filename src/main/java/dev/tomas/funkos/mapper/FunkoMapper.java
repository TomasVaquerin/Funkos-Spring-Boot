package dev.tomas.funkos.mapper;

import dev.tomas.funkos.dto.FunkoDto;
import dev.tomas.funkos.models.Funko;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class FunkoMapper {
    public Funko toFunko(UUID id, FunkoDto dto) {
        Funko funko = new Funko();
        funko.setId(id);
        funko.setNombre(dto.getNombre());
        funko.setTipo(dto.getTipo());
        funko.setPrecio(dto.getPrecio());
        funko.setCrateAt(LocalDateTime.now());
        funko.setUpdateAt(LocalDateTime.now());

        return funko;
    }

    public Funko toFunko(FunkoDto dto, Funko funko) {
        funko.setNombre(dto.getNombre());
        funko.setTipo(dto.getTipo());
        funko.setPrecio(dto.getPrecio());
        funko.getCrateAt();
        funko.setUpdateAt(LocalDateTime.now());
        return funko;
    }
}