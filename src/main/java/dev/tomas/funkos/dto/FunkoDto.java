package dev.tomas.funkos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor
public class FunkoDto {
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotBlank(message = "El tipo no puede estar vacio")
    private String tipo;
    @NotNull
    @DecimalMin("1.0" )
    private double precio;
}