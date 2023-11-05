package com.example.notasservice.Repository;

import com.example.notasservice.Entity.NotaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends CrudRepository<NotaEntity,Long> {
    List<NotaEntity> findAllByAlumno(int id);

}
