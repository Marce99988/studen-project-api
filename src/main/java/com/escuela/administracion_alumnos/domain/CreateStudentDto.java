package com.escuela.administracion_alumnos.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateStudentDto {

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @NotBlank(message ="El email es necesario")
    @Email(message = "formato de correo invalido")
    private String email;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 1, message = "Le edad minima es 1")

    private Integer age;

    @NotBlank(message = "El curso es obligatorio")
    private String course;
}