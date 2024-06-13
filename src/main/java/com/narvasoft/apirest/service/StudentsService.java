package com.narvasoft.apirest.service;
import com.narvasoft.apirest.models.Students;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface StudentsService {
    Iterable<Students> findAll();
    //Iterable<Students> findBySection(String section);
    Iterable<Students> findBySection(String section, Sort sort);
    Page<Students> findAll(Pageable pageable);
    Optional<Students> findById(Long id);
    Students save(Students student);
    void deleteById(Long id);
}


