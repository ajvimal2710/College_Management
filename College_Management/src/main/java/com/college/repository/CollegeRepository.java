package com.college.repository;

import org.springframework.data.repository.CrudRepository;

import com.college.entity.CollegeEntity;

public interface CollegeRepository extends CrudRepository<CollegeEntity, Integer>{

}
