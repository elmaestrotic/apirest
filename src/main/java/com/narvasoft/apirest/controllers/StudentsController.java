package com.narvasoft.apirest.controllers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.narvasoft.apirest.models.Asistencia;
import com.narvasoft.apirest.models.Students;
import com.narvasoft.apirest.service.AsistenciaService;
import com.narvasoft.apirest.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private AsistenciaService asistenciaService;

    // Create a new student
    @PostMapping
    public ResponseEntity<?> createAttendance(@RequestBody Asistencia asistencia, @RequestParam String studentId) {
        Long studentIdLong;
        try {
            studentIdLong = Long.parseLong(studentId);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid studentId format");
        }

        Optional<Students> oStudent = studentsService.findById(studentIdLong);
        if (!oStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        asistencia.setStudent(oStudent.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(asistenciaService.save(asistencia, studentIdLong));
    }

    // Get a specific student
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable(value = "id") Long id) {
        Optional<Students> student = studentsService.findById(id);
        if (!student.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student.get());
    }






    // ...
    @GetMapping("/section/{section}")
    public ResponseEntity<?> getStudentsBySection(@PathVariable(value = "section") String section) {
        Iterable<Students> students = studentsService.findBySection(section, Sort.by("name"));
        return ResponseEntity.ok(students);
    }

    // Get a student's attendance
    @GetMapping("/{id}/attendance")
    public ResponseEntity<?> getStudentAttendance(@PathVariable(value = "id") Long id) {
        Optional<Students> oStudent = studentsService.findById(id);
        if (!oStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Students student = oStudent.get();
        Pageable pageable = PageRequest.of(0, 1, Sort.by("date").descending());
        Iterable<Asistencia> attendanceRecords = asistenciaService.findByStudentId(student, pageable);
        return ResponseEntity.ok(attendanceRecords);
    }

    // Update a student
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Students studentDetails, @PathVariable(value = "id") Long id) {
        Optional<Students> student = studentsService.findById(id);
        if (!student.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        student.get().setName(studentDetails.getName());
        student.get().setEmail(studentDetails.getEmail());
        student.get().setPhotoUrl(studentDetails.getPhotoUrl());
        // student.get().setTotalLateArrivals(studentDetails.getTotalLateArrivals());
        return ResponseEntity.status(HttpStatus.CREATED).body(studentsService.save(student.get()));
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (!studentsService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        studentsService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Get all students
    @GetMapping
    public List<Students> readAll() {
        List<Students> students = StreamSupport
                .stream(studentsService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return students;
    }
}

