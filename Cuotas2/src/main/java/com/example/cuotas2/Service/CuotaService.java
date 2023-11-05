package com.example.cuotas2.Service;

import com.example.cuotas2.Entity.CuotaEntity;
import com.example.cuotas2.Models.Alumno;
import com.example.cuotas2.Models.Nota;
import com.example.cuotas2.Repository.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CuotaService {

    private final CuotaRepository cuotaRepository;

    @Autowired
    public CuotaService(CuotaRepository cuotaRepository) {
        this.cuotaRepository = cuotaRepository;
    }

    public List<CuotaEntity> obtenerCuotas() {
        return (List<CuotaEntity>) cuotaRepository.findAll();
    }

    public Optional<CuotaEntity> obtenerCuota(Long id) {
        return cuotaRepository.findById(id);
    }

    public Object actualizarCuota(Long id, CuotaEntity cuota) {
        Optional<CuotaEntity> cuotaEntity = cuotaRepository.findById(id);
        if (cuotaEntity.isPresent()){
            CuotaEntity cuotaUpdate = cuotaEntity.get();
            cuotaUpdate.setMonto(cuota.getMonto());
            cuotaUpdate.setFechaVencimiento(cuota.getFechaVencimiento());
            cuotaUpdate.setFechaPago(cuota.getFechaPago());
            cuotaUpdate.setStatus(cuota.getStatus());
            return cuotaRepository.save(cuotaUpdate);
        }
        return null;
    }

    public CuotaEntity agregarCuota(CuotaEntity cuota) {
        return cuotaRepository.save(cuota);
    }

    public void crearCuotas(Alumno alumno) {
        for (int i = 0; i < alumno.getCantidadCuotas(); i++) {
            CuotaEntity cuota = new CuotaEntity();
            cuota.setMonto((double) 1500000 /alumno.getCantidadCuotas());
            cuota.setFechaVencimiento(LocalDate.now().plusMonths(i+1));
            cuota.setAlumno(alumno.getId());
            cuota.setStatus("pendiente");
            cuota.setNumeroCuota(i+1);
            ponerDescuento(cuota, alumno.getAnoEgreso(), alumno.getTipoColegio());
            cuotaRepository.save(cuota);
        }
    }

    public void ponerDescuento(CuotaEntity cuota, int egreso, int tipoColegio){
        int desEgreso = 0;
        int desColegio = 0;
        if(LocalDate.now().getYear() - egreso <= 1){
            desEgreso = 15;
        } else if (LocalDate.now().getYear() - egreso <= 2){
            desEgreso = 8;
        } else if (LocalDate.now().getYear() - egreso <= 4){
            desEgreso = 4;
        }
        if (tipoColegio==1){
            desColegio = 10;
        } else if (tipoColegio==3){
            desColegio = 20;
        }
        cuota.setDescuento(desColegio + desEgreso);

    }

    public List<CuotaEntity> obtenerCuotasAlumno(Long id) {
        List<CuotaEntity> cuotas = cuotaRepository.findCuotaEntitiesByAlumno(id);
        /*
        for(CuotaEntity cuota : cuotas){
            if(cuota.getFechaVencimiento().isBefore(LocalDate.now()) && cuota.getStatus().equals("pendiente")){
                cuota.setStatus("vencida");
            }
            if (cuota.getStatus().equals("pendiente")){
                aplicarDescuento(cuota);
            }
        }}*/
        return cuotas;
    }

    public void pagarCuota(Long id) {
        Optional<CuotaEntity> cuotaEntity = cuotaRepository.findById(id);
        if (cuotaEntity.isPresent()){
            CuotaEntity cuotaUpdate = cuotaEntity.get();
            cuotaUpdate.setStatus("pagada");
            cuotaUpdate.setFechaPago(LocalDate.now());
            cuotaRepository.save(cuotaUpdate);
        }
    }

    public void actualizarNotas(List<Nota> notas){
        for (Nota nota : notas) {
            List<CuotaEntity> cuotas = cuotaRepository.findByAlumno(nota.getId());
            for (CuotaEntity cuota : cuotas) {
                LocalDate fechaVencimientoCuota = cuota.getFechaVencimiento();
                LocalDate fechaNota = nota.getFecha();
                if (fechaVencimientoCuota.isBefore(fechaNota) && !fechaVencimientoCuota.isBefore(LocalDate.now())) {
                    cuota.setCantidadNotas(cuota.getCantidadNotas() + 1);
                    cuota.setPtjeTotal(cuota.getPtjeTotal() + nota.getPuntaje());
                    cuotaRepository.save(cuota);
                }
            }
        }
    }

    public void aplicarDescuento(CuotaEntity cuota){
        double descuento = cuota.getDescuento();
        double monto = cuota.getMonto();
        int cantidadNotas = cuota.getCantidadNotas();
        int ptjeTotal = cuota.getPtjeTotal();
        double promedio = (double) ptjeTotal / cantidadNotas;
        if(1000>=promedio && promedio>=950){
            descuento += 10;
        } else if (promedio<949 && promedio>=900){
            descuento += 5;
        }else if (promedio<899 && promedio>=850){
            descuento += 2;
        }
        double montoNuevo = monto - (monto * (descuento/100));
        cuota.setMonto(Math.round(montoNuevo));
    }
}
