package com.example.notasservice.Controller;

import com.example.notasservice.Entity.NotaEntity;
import com.example.notasservice.Service.NotaService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/nota")
@CrossOrigin(origins = "http://localhost:3000/")

public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping("/subir")
    public ResponseEntity<String> subirCSV(@RequestParam MultipartFile file){
        try{

            List<NotaEntity> notas = notaService.leerCSV(file);
            System.out.println(notas);
            notaService.aplicarDescuentos(notas);
            return ResponseEntity.ok("Archivo subido correctamente");
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("No se pudo subir el archivo");
        }
    }

    @GetMapping("/alumno/{id}")
    public List<NotaEntity> obtenerNotasAlumno(@PathVariable int id){
        return notaService.obtenerNotasAlumno(id);

    }


}
