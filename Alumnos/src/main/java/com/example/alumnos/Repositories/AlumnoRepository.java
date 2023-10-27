package com.example.alumnos.Repositories;

import com.example.alumnos.Entities.AlumnoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends CrudRepository<AlumnoEntity,Long> {

}
