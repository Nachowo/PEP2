package com.example.cuotas.Repository;

import com.example.cuotas.Entity.CuotaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuotaRepository extends CrudRepository<CuotaEntity,Long> {
}
