package com.escuela.administracion_alumnos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    //Atributos de Alumnos
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    private String name;


    @NotBlank(message ="El email es necesario")
    @Email(message = "formato de correo invalido")
    private String email;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 1, message = "Le edad minima es 1")
    private Integer age;

    @NotBlank(message = "El curso es obligatorio")
    private String course;

    /*public StudentDto(Long id, String name, String email, int age, String course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }
    //Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    //Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }*/
}
