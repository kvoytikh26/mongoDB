package com.demo.model;

import com.demo.model.Address;
import com.demo.model.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Document
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;

    private List<String> favouriteSubjects;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime createdTime;

    public Student(String firstName, String lastName, String email,
                   Gender gender, Address address, List<String> favouriteSubjects,
                   BigDecimal totalSpentInBooks, LocalDateTime createdTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender =  gender;
        this.address = address;
        this.favouriteSubjects = favouriteSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
        this.createdTime = createdTime;
    }
}
