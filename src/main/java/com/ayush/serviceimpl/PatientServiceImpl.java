package com.ayush.serviceimpl;

import java.util.ArrayList;
import java.util.List;
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


	private final PatientRepository repo;


	@Override
	public List<PatientResponseDTO> getAllPatient() {
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
		Patient p=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Patient not found with this id"));
		
		if(p.getDeleted()) {
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
		Patient p=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Patient not found with this id"));
		if(p.getDeleted()) {
			throw new ResourceNotFoundException("Delete Patient can't update ");
		}
		
		p.setAge(dto.getAge());
		p.setName(dto.getName());
		p.setGender(dto.getGender());
		repo.save(p);
	}

	@Override
	public void deletePatient(Long id) {
		Patient p=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Patient not found with this id"));
		if(p.getDeleted()) {
			throw new ResourceNotFoundException("Patient already deleted");
		}
		p.setDeleted(true);
		repo.save(p);
		
	}

}
