package com.narvasoft.apirest.service;

import com.narvasoft.apirest.models.Asistencia;
import com.narvasoft.apirest.models.Students;
import com.narvasoft.apirest.repository.AsistenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private StudentsService studentsService;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Asistencia> findAll() {
        return asistenciaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Asistencia> findAll(Pageable pageable) {
        return asistenciaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Asistencia> findById(Long id) {
        return asistenciaRepository.findById(id);
    }

    @Override
    @Transactional
    public Asistencia save(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    @Override
    @Transactional
    public Asistencia save(Asistencia asistencia, Long studentId) {
        if (asistencia == null) {
            throw new IllegalArgumentException("Asistencia object is required");
        }
        if (studentId == null) {
            throw new IllegalArgumentException("studentId is required");
        }

        Students student = studentsService.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));

        asistencia.setStudent(student);
        return asistenciaRepository.save(asistencia);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        asistenciaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Asistencia> findByStudentId(Students student) {
        return asistenciaRepository.findByStudentId(student);
    }
}
