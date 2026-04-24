package com.ayush.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ayush.dto.DoctorRequestDTO;
import com.ayush.dto.DoctorResponseDTO;
import com.ayush.entity.Doctor;
import com.ayush.exception.ResourceNotFoundException;
import com.ayush.repository.DoctorRepository;
import com.ayush.service.DoctorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

	private static final Logger logger=LoggerFactory.getLogger(DoctorServiceImpl.class);
	private final DoctorRepository repo;
	
	@Override
	public Page<DoctorResponseDTO> getAllDoctors(int page, int size, String sortBy) {
		
		logger.info("Fetching all ActiveDoctors");
		
		Pageable pageable=PageRequest.of(page, size,Sort.by(sortBy));
		
		Page<Doctor> doctorPage=repo.findByDeletedFalse(pageable);
		
		List<DoctorResponseDTO> dtoList=new ArrayList<DoctorResponseDTO>();
		
		for(Doctor d:doctorPage.getContent()) {
			dtoList.add(mapToResponse(d));
		}
		
		
		return  new PageImpl<>(dtoList, pageable, doctorPage.getTotalElements());
	}
	

	@Override
	public DoctorResponseDTO getDoctorById(Long id) {
		
		logger.info("Fetching doctor with id: {}", id);
		
		Doctor d=repo.findById(id).orElseThrow(()->{
			logger.error("Doctor not found with this id:{}",id);
			throw new ResourceNotFoundException("Doctor not found with this id:"+id);
		});
		
		if(d.getDeleted()) {
			logger.warn("Doctor already deleted");
			throw new ResourceNotFoundException("Doctor already deleted:");
		}
		
		return mapToResponse(d);
	}

	@Override
	public DoctorResponseDTO createDoctor(DoctorRequestDTO dto) {

		 logger.info("Creating new doctor: {}", dto.getName());

	        Doctor doctor = new Doctor();
	        doctor.setName(dto.getName());
	        doctor.setSpeciality(dto.getSpeciality());
	        doctor.setPhone(dto.getPhone());

	        Doctor saved = repo.save(doctor);
	        return mapToResponse(saved);
	 
	}

	@Override
	public void updateDoctor(Long id, DoctorRequestDTO dto) {
		
		 logger.info("Updating doctor with id: {}", id);

	        Doctor d = repo.findById(id)
	                .orElseThrow(() -> {
	                    logger.error("Doctor not found with id: {}", id);
	                    return new ResourceNotFoundException("Doctor not found with this id");
	                });

	        if (d.getDeleted()) {
	            logger.warn("Attempt to update deleted doctor with id: {}", id);
	            throw new ResourceNotFoundException("Deleted doctor cannot be updated");
	        }

	        d.setName(dto.getName());
	        d.setSpeciality(dto.getSpeciality());
	        d.setPhone(dto.getPhone());
	        repo.save(d);

	        logger.info("Doctor updated successfully with id: {}", id);
	    
	}

	@Override
	public void deleteDoctor(Long id) {
     
		Doctor d = repo.findById(id)
                .orElseThrow(() -> {
                    logger.error("Doctor not found with id: {}", id);
                    return new ResourceNotFoundException("Doctor not found with this id");
                });

        if (d.getDeleted()) {
            logger.warn("Attempt to delete already deleted doctor with id: {}", id);
            throw new ResourceNotFoundException("Doctor already deleted");
        }

        d.setDeleted(true);
        repo.save(d);

        logger.info("Soft deleted doctor with id: {}", id);
		
	}

	private DoctorResponseDTO mapToResponse(Doctor d) {
		DoctorResponseDTO dto=new  DoctorResponseDTO();
		dto.setId(d.getId());
		dto.setName(d.getName());
		dto.setPhone(d.getPhone());
		dto.setSpeciality(d.getSpeciality());
		return dto;
	}
	

}

