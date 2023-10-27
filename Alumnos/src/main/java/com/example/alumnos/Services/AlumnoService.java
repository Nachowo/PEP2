package com.example.alumnos.Services;

import com.example.alumnos.Entities.AlumnoEntity;
import com.example.alumnos.Repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public AlumnoEntity agregarAlumno(AlumnoEntity alumno) {
        return alumnoRepository.save(alumno);
    }

    public Object obtenerAlumnos() {
        return alumnoRepository.findAll();
    }

    public Object obtenerAlumno(Long id) {
        return alumnoRepository.findById(id);
    }
}
