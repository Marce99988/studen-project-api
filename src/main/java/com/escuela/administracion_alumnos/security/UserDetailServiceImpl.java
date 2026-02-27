package com.escuela.administracion_alumnos.security;

import com.escuela.administracion_alumnos.entity.StudentEntity;
import com.escuela.administracion_alumnos.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         StudentEntity student = studentRepository.findOneByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado con " + email + "No existe"));
        return null;
    }
}