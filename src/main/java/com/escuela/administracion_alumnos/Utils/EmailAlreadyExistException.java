package com.escuela.administracion_alumnos.Utils;

public class EmailAlreadyExistException extends RuntimeException{

    public EmailAlreadyExistException(String message){
        super(message);
    }

}