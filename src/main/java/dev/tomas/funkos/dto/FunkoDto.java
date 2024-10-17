package dev.tomas.funkos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FunkoDto {
    private String nombre;
    private String tipo;
    private double precio;
}