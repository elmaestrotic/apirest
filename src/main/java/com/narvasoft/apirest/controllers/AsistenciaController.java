package com.narvasoft.apirest.controllers;

import com.narvasoft.apirest.models.Asistencia;
import com.narvasoft.apirest.models.AsistenciaData;
import com.narvasoft.apirest.models.Students;
import com.narvasoft.apirest.service.AsistenciaService;
import com.narvasoft.apirest.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private StudentsService studentsService;

    @PostMapping
    public ResponseEntity<?> createAsistencia(@RequestBody AsistenciaData asistenciaData) {
        try {
            if (asistenciaData.getAttendanceDate() == null) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Error creating attendance", "Attendance date is required"));
            }
            if (asistenciaData.getStudentId() == null) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Error creating attendance", "Student ID is required"));
            }

            Asistencia asistencia = new Asistencia();
            Students student = studentsService.findById(asistenciaData.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            asistencia.setStudent(student);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            asistencia.setAttendanceDate(LocalDate.parse((CharSequence) asistenciaData.getAttendanceDate(), formatter));

            asistencia.setAttended(asistenciaData.isAttended());
            asistencia.setArrivedLate(asistenciaData.isArrivedLate());
            asistencia.setDescription(asistenciaData.getDescription());
            asistencia.setAsignatura(asistenciaData.getAsignatura());

            Asistencia savedAsistencia = asistenciaService.save(asistencia);

            return ResponseEntity.status(HttpStatus.CREATED).body(new AsistenciaResponse(savedAsistencia.getId(), savedAsistencia));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error creating attendance", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readOne(@PathVariable(value = "id") Long id) {
        Optional<Asistencia> oAsistencia = asistenciaService.findById(id);
        if (!oAsistencia.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oAsistencia.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Asistencia asistenciaDetails, @PathVariable(value = "id") Long id) {
        try {
            Optional<Asistencia> oAsistencia = asistenciaService.findById(id);
            if (!oAsistencia.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            Asistencia asistencia = oAsistencia.get();

            // Actualizar los campos de la asistencia
            asistencia.setAttendanceDate(asistenciaDetails.getAttendanceDate());
            asistencia.setAttended(asistenciaDetails.isAttended());
            asistencia.setArrivedLate(asistenciaDetails.isArrivedLate());
            asistencia.setDescription(asistenciaDetails.getDescription());
            asistencia.setAsignatura(asistenciaDetails.getAsignatura());

            // Guardar la asistencia actualizada
            Asistencia updatedAsistencia = asistenciaService.save(asistencia);

            return ResponseEntity.ok(updatedAsistencia);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error updating attendance", e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (!asistenciaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        asistenciaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Asistencia> readAll() {
        return StreamSupport
                .stream(asistenciaService.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    private static class ErrorResponse {
        private String error;
        private String message;

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
    }

    private static class AsistenciaResponse {
        private Long id;
        private Asistencia asistencia;

        public AsistenciaResponse(Long id, Asistencia asistencia) {
            this.id = id;
            this.asistencia = asistencia;
        }

        public Long getId() {
            return id;
        }

        public Asistencia getAsistencia() {
            return asistencia;
        }
    }

    @GetMapping("/ultimo/{studentId}")//obtiene el último reg de asistencia del student con  ese ID
    public ResponseEntity<?> readLast(@PathVariable(value = "studentId") Long studentId) {
        Asistencia lastAsistencia = asistenciaService.findLastByStudentId(studentId);
        if (lastAsistencia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lastAsistencia);
    }


}

