package com.example.alumnos.Services;

import com.example.alumnos.Entities.AlumnoEntity;
import com.example.alumnos.Repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private RestTemplate restTemplate;
    private final AlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public AlumnoEntity agregarAlumno(AlumnoEntity alumno) {

        alumno = alumnoRepository.save(alumno);
        crearCuotas(alumno);
        return alumno;
    }

    private void crearCuotas(AlumnoEntity alumno) {
        restTemplate.postForObject("http://cuota-service/cuota/crearCuotas", alumno, void.class);
    }

    public List<AlumnoEntity> obtenerAlumnos() {
        return (List<AlumnoEntity>) alumnoRepository.findAll();
    }

    public Optional<AlumnoEntity> obtenerAlumno(Long id) {
        return alumnoRepository.findById(id);
    }
}
