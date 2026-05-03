package com.ayush.service;

import org.springframework.data.domain.Page;

import com.ayush.dto.PatientRequestDTO;
import com.ayush.dto.PatientResponseDTO;

public interface PatientService {
	
	public Page<PatientResponseDTO> getAllPatient(int page,int size,String sortBy);
	public PatientResponseDTO getPatientById(Long id);
	public PatientResponseDTO getByEmail(String email);
	public PatientResponseDTO createPatient(PatientRequestDTO dto);
	public void updatePatient(Long id,PatientRequestDTO dto);
	public void deletePatient(Long id);

}
