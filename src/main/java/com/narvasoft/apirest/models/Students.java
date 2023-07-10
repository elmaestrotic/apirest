package com.narvasoft.apirest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Students {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    //private Date registrationDate;
    private String photoUrl;  // Campo para la URL de la foto
    //private int totalLateArrivals;  // Este campo mantiene el total de llegadas tarde

    // Getters y Setters
}
