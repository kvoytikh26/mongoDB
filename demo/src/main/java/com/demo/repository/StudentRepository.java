package com.demo.repository;

import com.demo.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface StudentRepository
        extends MongoRepository<Student, String> {
    Optional<Student> findStudentByEmail(String email);

}
