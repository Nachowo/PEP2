package com.example.notasservice.Service;

import com.example.notasservice.Entity.NotaEntity;
import com.example.notasservice.Models.Alumno;
import com.example.notasservice.Repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotaService {


    private final NotaRepository notaRepository;

    @Autowired
    private NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    @Autowired
    private RestTemplate restTemplate;
    public List<NotaEntity> obtenerNotasAlumno(int id){
        return notaRepository.findAllByAlumno(id);
    }

    public List<NotaEntity> leerCSV(MultipartFile file) {
        List<NotaEntity> notas = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            br.readLine();

            String fila;
            while ((fila = br.readLine()) != null) {
                String[] casilla = fila.split(",");
                String rut = casilla[0];
                LocalDate fecha = LocalDate.parse(casilla[1], formatter);
                int puntaje = Integer.parseInt(casilla[2]);
                System.out.println(rut + " " + fecha + " " + puntaje);
                Alumno alumno = restTemplate.getForObject("http://alumno-service/alumno/rut/"+rut, Alumno.class);
                if (alumno != null) {
                    int id = alumno.getId();
                    NotaEntity notaEntity = new NotaEntity(id,puntaje, fecha);
                    notas.add(notaRepository.save(notaEntity));
                }

            }
            System.out.println(notas);
            return notas;
        } catch (Exception e) {
            return notas;
        }
    }

    public void aplicarDescuentos(List<NotaEntity> notas) {
        restTemplate.postForObject("http://cuota-service/cuota/actualizar", notas, void.class);
    }
}
