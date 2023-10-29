package com.example.cuotas2.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "cuotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private String status; //pagado - pendiente - vencida
    private int numeroCuota;
    private LocalDate fechaVencimiento;
    private double descuento;
    private Long alumno;
    private double monto;
    private LocalDate fechaPago;
}
