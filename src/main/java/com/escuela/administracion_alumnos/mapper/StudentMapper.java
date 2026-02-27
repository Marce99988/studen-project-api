package com.escuela.administracion_alumnos.mapper;
import com.escuela.administracion_alumnos.domain.CreateStudentDto;
import com.escuela.administracion_alumnos.domain.StudentDto;
import com.escuela.administracion_alumnos.entity.StudentEntity;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
    public interface StudentMapper {

       StudentDto toDto(StudentEntity entity);
       StudentEntity toEntity(StudentDto dto);

       CreateStudentDto toDtoExcludeId (StudentEntity entityTwo);
       StudentEntity toEntityTwo(CreateStudentDto dtoTwo);
    }
