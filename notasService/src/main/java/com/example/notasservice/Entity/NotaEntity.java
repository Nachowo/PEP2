package com.example.notasservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "notas")
@NoArgsConstructor
@AllArgsConstructor
public class NotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private String rut;
    private int puntaje;
    private LocalDate fecha;

    public NotaEntity(String rut, int puntaje, LocalDate fecha) {
        this.rut = rut;
        this.puntaje = puntaje;
        this.fecha = fecha;
    }
}
