package com.demo.controllers;

import com.demo.model.Student;
import com.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/all")
    public List<Student> fetchAllStudents() {
        return studentService.getAllStudents();
    }
}
