package com.narvasoft.apirest.service;

import com.narvasoft.apirest.models.Students;
import com.narvasoft.apirest.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Override
    public Iterable<Students> findAll() {
        return studentsRepository.findAll();
    }

    @Override
    public Page<Students> findAll(Pageable pageable) {
        return (Page<Students>) studentsRepository.findAll(pageable);
    }

    @Override
    public Optional<Students> findById(Long id) {
        return studentsRepository.findById(id);
    }

    @Override
    public Students save(Students student) {
        return studentsRepository.save(student);
    }

    @Override
    public void deleteById(Long id) {
        studentsRepository.deleteById(id);
    }
}
