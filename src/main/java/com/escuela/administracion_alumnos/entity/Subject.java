package com.escuela.administracion_alumnos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="el nombre de materia es requerido")
    private String nameSubject;

    @NotNull(message = "La cantidd de creditos no debe ser nulo")
    @Min(value = 1, message = "El minimo debe ser 1")
    private Integer Cantidadcreditos;

    @NotNull(message = "no puede ser nulo")
    private Integer semestre;

    @NotBlank(message = "Debe ingresar una descripcion")
    private String descripcion;


}