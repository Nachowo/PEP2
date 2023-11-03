package com.example.notasservice.Service;

import com.example.notasservice.Entity.NotaEntity;
import com.example.notasservice.Repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    @Autowired
    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }
    public List<NotaEntity> obtenerNotasAlumno(int id){
        return notaRepository.findAllByRut(id);
    }

    public boolean leerCSV(MultipartFile file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            br.readLine();

            String fila;
            while ((fila = br.readLine()) != null) {
                String[] casilla = fila.split(";");
                String rut = casilla[0];
                LocalDate fecha = LocalDate.parse(casilla[1], formatter);
                int puntaje = Integer.parseInt(casilla[2]);

                    NotaEntity notaEntity = new NotaEntity(rut,puntaje, fecha);
                    notaRepository.save(notaEntity);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
