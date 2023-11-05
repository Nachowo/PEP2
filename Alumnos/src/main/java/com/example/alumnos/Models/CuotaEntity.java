package com.example.alumnos.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuotaEntity {
    private Long id;
    private String status; //pagado - pendiente - vencida
    private int numeroCuota;
    private LocalDate fechaVencimiento;
    private double descuento;
    private Long alumno;
    private double monto;
    private LocalDate fechaPago;


    public double getMontoPagado() {
        if(this.status.equals("pagado")){
            return this.monto;
        }
        return 0;
    }
    public double getMontoPendiente() {
        if(!this.status.equals("pagado")){
            return this.monto;
        }
        return 0;
    }
}
