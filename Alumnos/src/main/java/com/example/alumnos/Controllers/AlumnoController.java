package com.example.alumnos.Controllers;


import com.example.alumnos.Entities.AlumnoEntity;
import com.example.alumnos.Models.Reporte;
import com.example.alumnos.Services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/alumno")
@CrossOrigin(origins = "http://localhost:3000/")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }



    @PostMapping
        public ResponseEntity<Map<String, Object>> agregarAlumno(@RequestBody AlumnoEntity alumno){
        System.out.println("se ingreso a agregar alumno con el siguiente alumno: " + alumno.toString());
            Map<String, Object> response = new HashMap<>();
            if (alumno != null){
                response.put("alumno", alumnoService.agregarAlumno(alumno));
                response.put("status", true);
                return ResponseEntity.ok(response);
            }
            response.put("status", false);
            return ResponseEntity.badRequest().body(response);
        }

    @GetMapping
    public ResponseEntity<Map<String, Object>> obtenerAlumnos(){
        Map<String, Object> response = new HashMap<>();
        response.put("alumnos", alumnoService.obtenerAlumnos());
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerAlumno(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        response.put("alumno", alumnoService.obtenerAlumno(id));
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<AlumnoEntity> obtenerAlumnoPorRut(@PathVariable String rut){
        AlumnoEntity alumno = alumnoService.obtenerAlumnoPorRut(rut);
        return ResponseEntity.ok(alumno);
    }

    @GetMapping("/reporte")
    public ResponseEntity<List<Reporte>> obtenerReporte(){
        List<Reporte> reportes = alumnoService.obtenerReporte();
        return ResponseEntity.ok(reportes);
    }



}
