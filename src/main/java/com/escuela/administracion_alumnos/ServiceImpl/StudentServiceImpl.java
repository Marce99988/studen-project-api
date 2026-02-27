package com.escuela.administracion_alumnos.ServiceImpl;

import com.escuela.administracion_alumnos.Utils.EmailAlreadyExistException;
import com.escuela.administracion_alumnos.domain.CreateStudentDto;
import com.escuela.administracion_alumnos.domain.StudentDto;
import com.escuela.administracion_alumnos.entity.StudentEntity;
import com.escuela.administracion_alumnos.mapper.StudentMapper;
import com.escuela.administracion_alumnos.repository.StudentRepository;
import com.escuela.administracion_alumnos.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper mapper;

    @Override
    public List<StudentEntity> getAlumnus(){
        return studentRepository.findAll();
    }

    @Override
    public Optional<StudentDto> getAlumnoEmail(String email){

        return studentRepository.findOneByEmailIgnoreCase(email)
                .map(mapper::toDto);
    }

    @Override
    public CreateStudentDto saveAlumnus(CreateStudentDto student){

        if(studentRepository.existsByemailIgnoreCase(student.getEmail())){
            throw new EmailAlreadyExistException("El usuario con correo " + student.getEmail()  +
                    " ya se encuentra en la BD");
        }

        StudentEntity entity = mapper.toEntityTwo(student);
        StudentEntity alumnocreado= studentRepository.save(entity);

        return mapper.toDtoExcludeId(alumnocreado);
    }

    @Override
    public Optional<StudentDto> updateStudent(Long id, StudentDto dto){
        return studentRepository.findById(id)
                .map(entity -> {
                    entity.setName(dto.getName());
                    entity.setAge(dto.getAge());
                    entity.setEmail(dto.getEmail());
                    entity.setCourse(dto.getCourse());

                    StudentEntity saveStudent = studentRepository.save(entity);
                    return mapper.toDto(saveStudent);
                });
    }
    /*@Override
    public StudentDto updateAlumnus(StudentDto student){

        StudentEntity alumnoExistingById = studentRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));;

        alumnoExistingById.setName(student.getName());
        alumnoExistingById.setEmail(student.getEmail());
        alumnoExistingById.setAge(student.getAge());
        alumnoExistingById.setCourse(student.getCourse());

        StudentEntity guardarAlumno = studentRepository.save(alumnoExistingById);

        return mapper.toDto(guardarAlumno);

        /*List<StudentEntity> lista = getAlumnus();
        for(StudentEntity s : lista){
            if(s.getId()== student.getId()){
                s.setName(student.getName());
                s.setEmail(student.getEmail());
                s.setAge(student.getAge());
                s.setCourse(student.getCourse());
                return studentRepository.save(s);
            }
        }*/

    @Override
    public Optional<StudentDto> patchAlumnus( Long id,StudentDto student) {

        return studentRepository.findById(id)
                .map(entity -> {

                    if(student.getName()!=null){
                        entity.setName(student.getName());
                    }
                    if(student.getAge()!=null){
                        entity.setAge(student.getAge());
                    }

                    if(student.getEmail()!=null){
                        if(studentRepository.existsByEmailIgnoreCaseAndIdNot(student.getEmail(),id)){
                            throw new EmailAlreadyExistException("El email ya existe en la BD");
                        }
                        entity.setEmail(student.getEmail());
                    }

                    if(student.getCourse()!=null){
                        entity.setCourse(student.getCourse());
                    }
                    StudentEntity guardarAlumno = studentRepository.save(entity);
                    return mapper.toDto(guardarAlumno);
                });
    }

    @Override
    public Optional<StudentDto> deleteAlumn(Long id){

    return studentRepository.findById(id)
                .map( student -> {
                    studentRepository.delete(student);

                return mapper.toDto(student);
                });
    }


}
