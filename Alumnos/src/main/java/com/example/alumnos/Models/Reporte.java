package com.example.alumnos.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    private String rut;
    private String nombre;
    private int cantidad_examenes;
    private double promedio_puntajes;
    private double monto_total_arancel;
    private String tipo_de_pago;
    private int cantidad_cuotas;
    private int cantidad_cuotas_pagadas;
    private double total_pagado;
    private LocalDate ultimo_pago;
    private double saldo_por_pagar;
    private int cantidad_cuotas_retraso;

    @Override
    public String toString() {
        return "Reporte{" +
                "rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cantidad_examenes=" + cantidad_examenes +
                ", promedio_puntajes=" + promedio_puntajes +
                ", monto_total_arancel=" + monto_total_arancel +
                ", tipo_de_pago='" + tipo_de_pago + '\'' +
                ", cantidad_cuotas=" + cantidad_cuotas +
                ", cantidad_cuotas_pagadas=" + cantidad_cuotas_pagadas +
                ", total_pagado=" + total_pagado +
                ", ultimo_pago=" + ultimo_pago +
                ", saldo_por_pagar=" + saldo_por_pagar +
                ", cantidad_cuotas_retraso=" + cantidad_cuotas_retraso +
                '}';
    }
}
