package com.example.alumnos.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="alumnos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rut;
    private String nombre1;
    private String apellido;
    private LocalDate nacimiento;
    private int tipoColegio; // 1 . subvencionado // 2 - privado // 3 - Municipal
    private String nombreColegio;
    private int anoEgreso;
    private boolean cuotasGeneradas;
    private int tipoPago; //0 - Sin definir // 1 - contado // 2 - cuotas
    private int cantidadCuotas;
}
