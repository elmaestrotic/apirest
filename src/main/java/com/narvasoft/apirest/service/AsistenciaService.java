package com.narvasoft.apirest.service;

import com.narvasoft.apirest.models.Asistencia;
import com.narvasoft.apirest.models.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AsistenciaService {
    Iterable<Asistencia> findAll();
    Page<Asistencia> findAll(Pageable pageable);
    Optional<Asistencia> findById(Long id);

    Iterable<Asistencia> findByStudentId(Students student, Pageable pageable);

    Asistencia save(Asistencia usuarios);
    @Transactional
    Asistencia save(Asistencia asistencia, Long studentId);
    void deleteById(Long id);
    Iterable<Asistencia> findByStudentId(Long id);

    Page<Asistencia> findByStudentId(Long id, Pageable pageable);

    Asistencia findLastByStudentId(Long id);
}

