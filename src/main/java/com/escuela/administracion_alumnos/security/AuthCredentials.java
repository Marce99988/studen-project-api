package com.escuela.administracion_alumnos.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;

}