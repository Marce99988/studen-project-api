package com.escuela.administracion_alumnos;

import com.escuela.administracion_alumnos.entity.StudentEntity;
import com.escuela.administracion_alumnos.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SistemaDeManejoDeAlumnosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeManejoDeAlumnosApplication.class, args);
	}

    /*@Bean
    CommandLineRunner init(StudentRepository repo) {
        return args -> {
            repo.save(new StudentEntity(1000L,"Marcelino","marcelino.guerra",
                    18,"Quinto"));
        };
    }*/
}
