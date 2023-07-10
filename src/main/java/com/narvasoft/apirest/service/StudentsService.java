package com.narvasoft.apirest.service;




import com.narvasoft.apirest.models.Students;
import java.util.Optional;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentsService {
    Iterable<Students> findAll();
    Page<Students> findAll(Pageable pageable);
    Optional<Students> findById(Long id);
    Students save(Students student);
    void deleteById(Long id);
}


