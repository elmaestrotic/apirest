package com.narvasoft.apirest.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Students student;

    private Date attendanceDate;
    private boolean attended;
    private boolean llegoTarde;
    private String description;

    private String asignatura;

    // Getters y Setters

}
