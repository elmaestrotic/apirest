package com.narvasoft.apirest.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.Data;



import java.util.Date;

@Table(name = "attendance")
@ToString
@Getter
@Setter
@EqualsAndHashCode


@Data
@Entity
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Students student; // Relación ManyToOne con Students
    private String attendanceDate;
    private boolean attended;
    private boolean arrivedLate;
    private String description;
    private String asignatura;
}