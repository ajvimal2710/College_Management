package com.college.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "college")
public class CollegeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer collegeId;
    private String collegeName;
    private Integer capacity;
    private String department;
    private Integer sections; 
}
