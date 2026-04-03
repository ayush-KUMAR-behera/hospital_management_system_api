package com.ayush.service;

import java.util.List;

import com.ayush.dto.PatientRequestDTO;
import com.ayush.dto.PatientResponseDTO;

public interface PatientService {
	
	public List<PatientResponseDTO> getAllPatient();
	public PatientResponseDTO getPatientById(Long id);
	public PatientResponseDTO createPatient(PatientRequestDTO dto);
	public void updatePatient(Long id,PatientRequestDTO dto);
	public void deletePatient(Long id);

}
