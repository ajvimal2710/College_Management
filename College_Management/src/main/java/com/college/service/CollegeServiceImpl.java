package com.college.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.college.dto.CollegeDTO;
import com.college.entity.CollegeEntity;
import com.college.exception.CollegeException;
import com.college.repository.CollegeRepository;

@Service
@Transactional
public class CollegeServiceImpl implements CollegeService{

    @Autowired
    private CollegeRepository CollegeRepository;

    @Override
    public List<CollegeDTO> getAllColleges() throws CollegeException {
        List<CollegeEntity> CollegeEntities = (List<CollegeEntity>) CollegeRepository.findAll();
        if(CollegeEntities.isEmpty()){
            throw new CollegeException("No Colleges Found");
        }
        List<CollegeDTO> CollegeDTOs = new ArrayList<>();
        for(CollegeEntity College : CollegeEntities){
            CollegeDTO CollegeDTO = entityToDTO(College);
            CollegeDTOs.add(CollegeDTO);
        }
        return CollegeDTOs;
    }

    @Override
    public CollegeDTO getCollegeById(Integer CollegeId) throws CollegeException {
        Optional<CollegeEntity> optional = CollegeRepository.findById(CollegeId);
        if(optional.isEmpty()){
            throw new CollegeException("College Not Found");
        }
        CollegeDTO CollegeDTO = entityToDTO(optional.get());
        return CollegeDTO;
    }

    @Override
    public CollegeDTO createCollege(CollegeDTO CollegeDTO) throws CollegeException {
        CollegeEntity entity = DTOToEntity(CollegeDTO);
        CollegeEntity College = CollegeRepository.save(entity);
        CollegeDTO CollegeDTOReturned = entityToDTO(College);
        return CollegeDTOReturned;
    }

    @Override
    public CollegeDTO updateDepartmentCapacity(Integer CollegeId, Integer capacity) throws CollegeException {
        Optional<CollegeEntity> optional = CollegeRepository.findById(CollegeId);
        if(optional.isEmpty()){
            throw new CollegeException("College Not Found");
        }
        CollegeEntity CollegeEntity = optional.get();
        CollegeEntity.setCapacity(capacity);
        CollegeRepository.save(CollegeEntity);
        CollegeDTO CollegeDTO = entityToDTO(CollegeEntity);
        return CollegeDTO;
    }

    @Override
    public Integer deleteCollege(Integer CollegeId) throws CollegeException {
        Optional<CollegeEntity> optional = CollegeRepository.findById(CollegeId);
        if(optional.isEmpty()){
            throw new CollegeException("College Not Found");
        }
        CollegeRepository.deleteById(CollegeId);
         Optional<CollegeEntity> optionalCheck = CollegeRepository.findById(CollegeId);
        if(!optionalCheck.isEmpty()){
            throw new CollegeException("College Not Found");
        }
        return CollegeId;
    }

    private CollegeDTO entityToDTO(CollegeEntity college){
        CollegeDTO collegeDTO = new CollegeDTO();
        collegeDTO.setCollegeId(college.getCollegeId());
        collegeDTO.setCollegeName(college.getCollegeName());
        collegeDTO.setDepartment(college.getDepartment());
        collegeDTO.setCapacity(college.getCapacity());
        collegeDTO.setSections(college.getSections());
        return collegeDTO;
    }

    private CollegeEntity DTOToEntity(CollegeDTO collegeDTO){
        CollegeEntity collegeEntity = new CollegeEntity();
        collegeEntity.setCollegeName(collegeDTO.getCollegeName());
        collegeEntity.setDepartment(collegeDTO.getDepartment());
        collegeEntity.setCapacity(collegeDTO.getCapacity());
        collegeEntity.setSections(collegeDTO.getSections());
        return collegeEntity;
    }

}
