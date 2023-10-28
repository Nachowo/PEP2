package com.example.cuotas.Service;

import com.example.cuotas.Entity.CuotaEntity;
import com.example.cuotas.Models.Alumno;
import com.example.cuotas.Repository.CuotaRepository;
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
            cuota.setMonto(1500000);
            cuota.setFechaVencimiento(LocalDate.now().plusMonths(i));
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
}
