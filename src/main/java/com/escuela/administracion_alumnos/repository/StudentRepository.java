package com.escuela.administracion_alumnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.escuela.administracion_alumnos.entity.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findOneByEmailIgnoreCase(String email);

    boolean existsByemailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndIdNot(String email,Long id);

    StudentEntity deleteById(StudentEntity entity);
}
