package com.ayush.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ayush.dto.PatientRequestDTO;
import com.ayush.dto.PatientResponseDTO;
import com.ayush.entity.Patient;
import com.ayush.exception.ResourceNotFoundException;
import com.ayush.repository.PatientRepository;
import com.ayush.service.PatientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

	private static final Logger logger=LoggerFactory.getLogger(PatientServiceImpl.class);
	private final PatientRepository repo;


	@Override
	public List<PatientResponseDTO> getAllPatient() {
		
		logger.info("Fetching all active patients");
		
		List<Patient> list=repo.findByDeletedFalse();
		List<PatientResponseDTO> dtolist=new ArrayList<PatientResponseDTO>();
		for(Patient p:list) {
			PatientResponseDTO dto=new PatientResponseDTO();
			dto.setId(p.getId());
			dto.setAge(p.getAge());
			dto.setGender(p.getGender());
			dto.setName(p.getName());
			dtolist.add(dto);
		}
		return dtolist;
	}

	@Override
	public PatientResponseDTO getPatientById(Long id) {
		
		logger.info("Fetching patient with id: {}",id);
		
		Patient p = repo.findById(id)
	            .orElseThrow(() -> {
	            	
	                logger.error("Patient not found with id: {}", id);
	                
	                return new ResourceNotFoundException("Patient not found");
	            });
		
		if(p.getDeleted()) {
			
			logger.warn("Attemt to access deleted patient with id: {}", id);
			
			throw new ResourceNotFoundException("Patient not found");
		}
		

		
		PatientResponseDTO dto=new PatientResponseDTO();
		dto.setId(p.getId());
		dto.setName(p.getName());
		dto.setAge(p.getAge());
		dto.setGender(p.getGender());
		return dto;
	}

	@Override
	public PatientResponseDTO createPatient(PatientRequestDTO dto) {
		
		logger.info("creating new patient: {}",dto.getName());
		
	  Patient patient=new Patient();
	  patient.setName(dto.getName());
	  patient.setAge(dto.getAge());
	  patient.setGender(dto.getGender());
	  Patient p=repo.save(patient);
		PatientResponseDTO res=new PatientResponseDTO();
		res.setId(p.getId());
		res.setName(p.getName());
		res.setAge(p.getAge());
		res.setGender(p.getGender());
		return res;
	}

	@Override
	public void updatePatient(Long id, PatientRequestDTO dto) {
		
		logger.info("Updating patients:{}",dto.getName());
		
		Patient p=repo.findById(id).orElseThrow(()->{
			
			logger.error("Patient not found with with id:{}",id);
			
			return new ResourceNotFoundException("Patient not found with this id");
		});
		
		
		if(p.getDeleted()) {
			
			logger.warn("Attempt to update deleted patient with id: {}",id);
			
			throw new ResourceNotFoundException("Delete Patient can't update ");
		}
		
		
		
		p.setAge(dto.getAge());
		p.setName(dto.getName());
		p.setGender(dto.getGender());
		repo.save(p);
	}

	@Override
	public void deletePatient(Long id) {
		Patient p=repo.findById(id).orElseThrow(()->{
			
		logger.error("Patient not found with id:{}",id);	
			
		return new ResourceNotFoundException("Patient not found with this id");
		});
		
		if(p.getDeleted()) {
			
		logger.warn("Attempt to access deleted patient with id: {}", id);
			
			throw new ResourceNotFoundException("Patient already deleted");
		}
		
		p.setDeleted(true);
		repo.save(p);
		
		logger.info("Soft deleting patient with id: {}",id);
		
	}

}
