package com.college.service;

import java.util.List;

import com.college.dto.CollegeDTO;
import com.college.exception.CollegeException;

public interface CollegeService {

    public List<CollegeDTO> getAllColleges() throws CollegeException;
    public CollegeDTO getCollegeById(Integer CollegeId) throws CollegeException;
    public CollegeDTO createCollege(CollegeDTO CollegeDTO) throws CollegeException;
    public CollegeDTO updateDepartmentCapacity(Integer CollegeId, Integer capacity) throws CollegeException;
    public Integer deleteCollege(Integer CollegeId) throws CollegeException;
}
