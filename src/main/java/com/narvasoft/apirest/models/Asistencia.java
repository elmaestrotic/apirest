package com.narvasoft.apirest.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.Data;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

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
    private LocalDate attendanceDate;
    private boolean attended;
    private boolean arrivedLate;
    private String description;
    private String asignatura;


/*public String getFormattedAttendanceDate() {
    if (attendanceDate == null || attendanceDate.isEmpty()) {
        return null; // or some default value
    }
    DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(attendanceDate, parser);
        return formatter.format(zonedDateTime);
    } catch (DateTimeParseException e) {
        System.out.println("Failed to parse date: " + attendanceDate);
        e.printStackTrace();
        return null;
    }
}*/
}