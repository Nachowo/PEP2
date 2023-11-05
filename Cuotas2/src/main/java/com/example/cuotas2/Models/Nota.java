package com.example.cuotas2.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nota {
    private Long id;
    private String rut;
    private int puntaje;
    private LocalDate fecha;

    public Nota(String rut, int puntaje, LocalDate fecha) {
        this.rut = rut;
        this.puntaje = puntaje;
        this.fecha = fecha;
    }
}
