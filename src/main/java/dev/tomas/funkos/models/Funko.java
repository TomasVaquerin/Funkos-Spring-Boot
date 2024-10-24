package dev.tomas.funkos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funko {
    private Long numero;
    private UUID id;
    private String nombre;
    private String tipo;
    private double precio;
    private LocalDateTime crateAt;
    private LocalDateTime updateAt;
}