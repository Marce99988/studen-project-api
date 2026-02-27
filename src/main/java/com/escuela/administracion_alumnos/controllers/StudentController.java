package com.escuela.administracion_alumnos.controllers;

import com.escuela.administracion_alumnos.domain.CreateStudentDto;
import com.escuela.administracion_alumnos.domain.StudentDto;
import com.escuela.administracion_alumnos.entity.StudentEntity;
import com.escuela.administracion_alumnos.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    String mensaje=null;

  @GetMapping
  public List<StudentEntity> getStudents(){
      return studentService.getAlumnus();
  }

    @GetMapping("/{email}")
    public ResponseEntity<StudentDto> getAlumnoEmail( @PathVariable String email){

        return studentService.getAlumnoEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

  @PostMapping("/save")
  public ResponseEntity<?> postAlumno(@Valid @RequestBody CreateStudentDto student ){
        CreateStudentDto alumnoCreado = studentService.saveAlumnus(student);
      return ResponseEntity.status(HttpStatus.CREATED).body("El usuario " + student.getName()
              + " Ha Sido creado");
  }

  @PutMapping("/{id}")
    public ResponseEntity<?> putAlumno(@Valid @PathVariable Long id,@RequestBody StudentDto student){

      if(student==null){
          mensaje="No vienen datos en la peticion";
          return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensaje);
      }

      return studentService.updateStudent(id,student)
              .map(ResponseEntity::ok)
              .orElseGet(() -> ResponseEntity.notFound().build());

      /*StudentDto alumnoActualizado = studentService.updateAlumnus(student);
      if(alumnoActualizado != null){
          return ResponseEntity.ok(alumnoActualizado);
      }else{
          String mensaje = "El usuario con ID " + student.getId() + "No fue encontrado";
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
      }*/
  }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> patchAlumno(@PathVariable Long id,@Valid @RequestBody StudentDto student){
      return studentService.patchAlumnus(id,student)
              .map(ResponseEntity::ok)
              .orElseGet(()-> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteStudent(@PathVariable Long id){

      Optional<StudentDto> alumnoEliminado = studentService.deleteAlumn(id);
      return alumnoEliminado
              .map(studentDto -> ResponseEntity.ok(alumnoEliminado))
              .orElseGet(()->ResponseEntity.notFound().build());
  }

}
