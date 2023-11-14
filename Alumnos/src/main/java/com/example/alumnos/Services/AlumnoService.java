package com.example.alumnos.Services;

import com.example.alumnos.Entities.AlumnoEntity;
import com.example.alumnos.Models.CuotaEntity;
import com.example.alumnos.Models.Nota;
import com.example.alumnos.Models.Reporte;
import com.example.alumnos.Repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private RestTemplate restTemplate;
    private final AlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public AlumnoEntity agregarAlumno(AlumnoEntity alumno) {
        if(alumno.getTipoPago() == 1){
            alumno.setCantidadCuotas(1);
        }
        alumno = alumnoRepository.save(alumno);
        crearCuotas(alumno);
        return alumno;
    }

    private void crearCuotas(AlumnoEntity alumno) {
        restTemplate.postForObject("http://cuota-service/cuota/crearCuotas", alumno, void.class);
    }

    public List<AlumnoEntity> obtenerAlumnos() {
        return (List<AlumnoEntity>) alumnoRepository.findAll();
    }

    public Optional<AlumnoEntity> obtenerAlumno(Long id) {
        return alumnoRepository.findById(id);
    }

    public AlumnoEntity obtenerAlumnoPorRut(String rut) {
        return alumnoRepository.findByRut(rut);
    }

    public List<Reporte> obtenerReporte() {
        List<Reporte> reportes = new ArrayList<>();
        List<AlumnoEntity> alumnos = (List<AlumnoEntity>) alumnoRepository.findAll();
        System.out.println(alumnos);
        for (AlumnoEntity alumno : alumnos) {

            try{
                ResponseEntity<List<CuotaEntity>> responseEntity = restTemplate.exchange(
                        "http://cuota-service/cuota/alumno/" + alumno.getId(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<CuotaEntity>>() {});
                List<CuotaEntity> cuotas = responseEntity.getBody();
                ResponseEntity<List<Nota>> notas1 = restTemplate.exchange(
                        "http://nota-service/nota/alumno/" + alumno.getId(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Nota>>() {});
                List<Nota> notas = notas1.getBody();
                System.out.println("notas: " + notas);
                int examenes = 0;
                if (notas != null) {
                    examenes = notas.size();
                }

                double promedio = calcularPromedio(notas);
                if(cuotas!=null){
                    Reporte reporte = new Reporte();
                    //rut
                    reporte.setRut(alumno.getRut());

                    //nombre
                    reporte.setNombre(alumno.getNombre1());
                    System.out.println("set cantidad examens");
                    //cantidad_examenes
                    reporte.setCantidad_examenes(examenes);

                    System.out.println("set promedio");
                    //promedio
                    reporte.setPromedio_puntajes(promedio);

                    System.out.println("set monto total arancel");
                    //total a pagar
                    reporte.setMonto_total_arancel(calcularTotalPago(cuotas));

                    System.out.println("set tipo de pago");
                    //tipo de pago
                    reporte.setTipo_de_pago(alumno.tipoPagoStr());

                    System.out.println("set cantidad de cuotas");
                    //total de cuotas
                    reporte.setCantidad_cuotas(cuotas.size());

                    //cuotas pagadas
                    System.out.println("cantidad de cuotas pagadas");
                    reporte.setCantidad_cuotas_pagadas((int) cuotas.stream().filter(cuota -> cuota.getStatus().equals("pagado")).count());

                    System.out.println("total pagado");
                    //total pagado
                    reporte.setTotal_pagado(cuotas.stream().mapToDouble(CuotaEntity::getMontoPagado).sum());

                    System.out.println("ultimo pago");
                    //ultimo pago
                    reporte.setUltimo_pago(getUltimoPago(cuotas));

                    System.out.println("saldo por pagar");
                    //saldo por pagar
                    reporte.setSaldo_por_pagar(cuotas.stream().mapToDouble(CuotaEntity::getMontoPendiente).sum());

                    System.out.println("cuotas con retraso");
                    //cuotas con retraso
                    reporte.setCantidad_cuotas_retraso((int) cuotas.stream().filter(cuota -> cuota.getStatus().equals("retraso")).count());

                    reportes.add(reporte);
                }
            }catch (Exception e){
                System.out.println("error en el servicio de cuotas");
                System.out.println(e);
            }


        }
        return reportes;
    }

    private LocalDate getUltimoPago(List<CuotaEntity> cuotas) {
        LocalDate ultimo_pago = null;
        for (CuotaEntity cuota : cuotas) {
            if (cuota.getStatus().equals("pagado")) {
                if (ultimo_pago == null) {
                    ultimo_pago = cuota.getFechaPago();
                } else if (cuota.getFechaPago().isAfter(ultimo_pago)) {
                    ultimo_pago = cuota.getFechaPago();
                }
            }
        }
        return ultimo_pago;
    }

    private double calcularPromedio(List<Nota> notas) {
        if (notas == null) {
            return 0;
        }
        return notas.stream().mapToInt(Nota::getPuntaje).average().orElse(0);
    }

    private double calcularTotalPago(List<CuotaEntity> cuotas) {
        double total_pago = 0;
        for (CuotaEntity cuota : cuotas) {
            if (cuota.getStatus().equalsIgnoreCase("pagado")){
                total_pago+=cuota.getMonto();
            }else{
                total_pago += cuota.getMonto();
            }
        }
        return total_pago;
    }


}
