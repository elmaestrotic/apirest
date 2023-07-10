package com.narvasoft.apirest.models;


import lombok.Data;
//Esta clase es un DTO (Data Transfer Object) que se utiliza para recibir datos de la solicitud HTTP.
import java.util.Date;


@Data
public class AsistenciaData {
    private Long studentId;
    private Date attendanceDate;
    private boolean attended;
    private boolean arrivedLate;
    private String description;
    private String asignatura;
}
