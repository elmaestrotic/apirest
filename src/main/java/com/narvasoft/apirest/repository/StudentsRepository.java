package com.narvasoft.apirest.repository;

import com.narvasoft.apirest.models.Students;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//public interface StudentsRepository extends JpaRepository<Students, Long> {}
@Repository
public interface StudentsRepository extends CrudRepository<Students, Long> {
    Object findAll(Pageable pageable);
}