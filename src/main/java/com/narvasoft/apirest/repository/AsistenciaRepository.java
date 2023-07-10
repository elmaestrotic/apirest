package com.narvasoft.apirest.repository;

import com.narvasoft.apirest.models.Asistencia;
import com.narvasoft.apirest.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    Iterable<Asistencia> findByStudentId(Students student);
}
