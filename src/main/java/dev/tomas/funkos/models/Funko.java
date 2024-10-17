package dev.tomas.funkos.models;

import dev.tomas.funkos.dto.FunkoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Funko {
    private UUID id;
    private String nombre;
    private String tipo;
    private double precio;
    private LocalDateTime crateAt;
    private LocalDateTime updateAt;

    public Funko() {
        this.id = UUID.randomUUID();
        this.crateAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public void update(FunkoDto dto) {
        this.nombre = dto.getNombre();
        this.tipo = dto.getTipo();
        this.precio = dto.getPrecio();
        this.updateAt = LocalDateTime.now();
    }
}