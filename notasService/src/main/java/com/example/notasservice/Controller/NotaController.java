package com.example.notasservice.Controller;

import com.example.notasservice.Service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping("/subir")
    public String subirCSV(@RequestParam MultipartFile file){
        notaService.leerCSV(file);
        return "Principal";
    }

    @GetMapping("/alumno/{id}")
    public String obtenerNotasAlumno(@RequestParam int id){
        notaService.obtenerNotasAlumno(id);
        return "Principal";
    }


}
