package com.narvasoft.apirest.service;

import com.narvasoft.apirest.models.Asistencia;
import com.narvasoft.apirest.models.Students;
import com.narvasoft.apirest.repository.AsistenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
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
    public Iterable<Asistencia> findByStudentId(Students student, Pageable pageable) {
        return asistenciaRepository.findByStudentId(student.getId(), pageable);
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

    // No need to format the attendance date before saving
    // asistencia.setAttendanceDate(formatter.format(zonedDateTime));

    return asistenciaRepository.save(asistencia);
}

    @Override
    @Transactional
    public void deleteById(Long id) {
        asistenciaRepository.deleteById(id);
    }

    @Override
    public Iterable<Asistencia> findByStudentId(Long id) {
        Students student = studentsService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
        return asistenciaRepository.findByStudentId(student.getId());
    }

    @Override
    public Page<Asistencia> findByStudentId(Long id, Pageable pageable) {
        Students student = studentsService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
        return asistenciaRepository.findByStudentId(student.getId(), pageable);
    }



    @Override
public Asistencia findLastByStudentId(Long id) {
    Students student = studentsService.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
    Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id"));
    Page<Asistencia> page = asistenciaRepository.findByStudentId(student.getId(), pageable);
    if (page.hasContent()) {
        return page.getContent().get(0);
    } else {
        throw new EntityNotFoundException("No attendance record found for student with ID: " + id);
    }
}



   /* @Override
    public Asistencia findLastByStudentId(Long id) {
        Students student = studentsService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "attendanceDate"));
        Page<Asistencia> page = asistenciaRepository.findByStudentId(student.getId(), pageable);
        if (page.hasContent()) {
            return page.getContent().get(0);
        } else {
            throw new EntityNotFoundException("No attendance record found for student with ID: " + id);
        }
    }*/



   /* @Override
    @Transactional(readOnly = true)
    public Iterable<Asistencia> findByStudentId(Students student) {
        return asistenciaRepository.findByStudentId(student);
    }*/
}
