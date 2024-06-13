package com.narvasoft.apirest.repository;

import com.narvasoft.apirest.models.Asistencia;
import com.narvasoft.apirest.models.Students;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    Iterable<Asistencia> findByStudentId(Long id);
    Page<Asistencia> findByStudentId(Long id, Pageable pageable);

    @Query(value = "SELECT a FROM Asistencia a WHERE a.student.id = :studentId ORDER BY a.attendanceDate DESC")
    List<Asistencia> findLastByStudentId(@Param("studentId") Long studentId);
}