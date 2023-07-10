package com.narvasoft.apirest.service;

import com.narvasoft.apirest.models.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuariosService {
    public Iterable <Usuarios> findAll();
    public Page<Usuarios> findAll(Pageable pageable);
    public Optional<Usuarios> findById(Long id);
    public Usuarios save(Usuarios usuarios);
    public void deleteById(Long id);
}
