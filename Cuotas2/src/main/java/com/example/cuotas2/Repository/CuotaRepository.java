package com.example.cuotas2.Repository;

import com.example.cuotas2.Entity.CuotaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuotaRepository extends CrudRepository<CuotaEntity,Long> {
    List<CuotaEntity> findByAlumno(Long id);
    List<CuotaEntity> findCuotaEntitiesByAlumnoOrderByNumeroCuota(Long id);

    List<CuotaEntity> findCuotaEntitiesByAlumno(Long id);
}
