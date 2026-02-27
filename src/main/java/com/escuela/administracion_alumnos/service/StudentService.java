package com.escuela.administracion_alumnos.service;

import com.escuela.administracion_alumnos.domain.CreateStudentDto;
import com.escuela.administracion_alumnos.domain.StudentDto;
import com.escuela.administracion_alumnos.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentEntity> getAlumnus();

    Optional<StudentDto> getAlumnoEmail(String email);

    CreateStudentDto saveAlumnus(CreateStudentDto student);

    Optional<StudentDto> updateStudent(Long id, StudentDto dto);

    Optional<StudentDto> patchAlumnus(Long id, StudentDto student);

    Optional<StudentDto> deleteAlumn(Long id);
}
