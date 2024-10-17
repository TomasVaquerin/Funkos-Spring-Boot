package dev.tomas.funkos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}