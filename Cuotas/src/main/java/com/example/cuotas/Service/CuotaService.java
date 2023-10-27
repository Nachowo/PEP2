package com.example.cuotas.Service;

import com.example.cuotas.Entity.CuotaEntity;
import com.example.cuotas.Repository.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
