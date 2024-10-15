package dev.tomas.funkos.mapper;

import dev.tomas.funkos.dto.FunkoDto;
import dev.tomas.funkos.models.Funko;
import org.springframework.stereotype.Component;


@Component
public class FunkoMapper {
    public Funko toFunko(FunkoDto dto) {
        Funko funko = new Funko();
        funko.setNombre(dto.getNombre());
        funko.setTipo(dto.getTipo());
        funko.setPrecio(dto.getPrecio());
        return funko;
    }
}
