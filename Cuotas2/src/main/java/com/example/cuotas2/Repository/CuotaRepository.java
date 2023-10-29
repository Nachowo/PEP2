package com.example.cuotas2.Repository;

import com.example.cuotas2.Entity.CuotaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuotaRepository extends CrudRepository<CuotaEntity,Long> {
}
