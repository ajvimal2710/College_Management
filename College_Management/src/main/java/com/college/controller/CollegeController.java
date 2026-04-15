package com.college.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.dto.CollegeDTO;
import com.college.exception.CollegeException;
import com.college.service.CollegeService;

@RestController
@RequestMapping("/api/college")
public class CollegeController {

    @Autowired
    private CollegeService CollegeService;

    @GetMapping("/getall")
    public ResponseEntity<List<CollegeDTO>> getAllColleges() throws CollegeException{
        List<CollegeDTO>CollegeDTOs =  CollegeService.getAllColleges();
        return new ResponseEntity<>(CollegeDTOs, HttpStatus.OK);
    }

    @GetMapping("/get/{CollegeId}")
    public ResponseEntity<CollegeDTO> getCollegeById(@PathVariable Integer CollegeId) throws CollegeException{
        CollegeDTO CollegeDTO =  CollegeService.getCollegeById(CollegeId);
        return new ResponseEntity<>(CollegeDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CollegeDTO> createCollege(@RequestBody CollegeDTO CollegeDTO) throws CollegeException{
        CollegeDTO CollegeDTOReturned =  CollegeService.createCollege(CollegeDTO);
        return new ResponseEntity<>(CollegeDTOReturned, HttpStatus.CREATED);
    }

    @PutMapping("/put/{CollegeId}/{capacity}")
    public ResponseEntity<CollegeDTO> updateDepartmentCapacity(@PathVariable Integer CollegeId, @PathVariable Integer capacity) throws CollegeException{
        CollegeDTO CollegeDTO =  CollegeService.updateDepartmentCapacity(CollegeId, capacity);
        return new ResponseEntity<>(CollegeDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{CollegeId}")
    public ResponseEntity<String> deleteCollege(@PathVariable Integer CollegeId) throws CollegeException{
        Integer CollegeIdReturned = CollegeService.deleteCollege(CollegeId);
        String response = "College deleted successfully: "+CollegeIdReturned;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
