package com.ayush.service;

import java.util.List;

import com.ayush.entity.Patient;

public interface PatientService {
	
	public List<Patient> getAllPatient();
	public Patient getPatientById(Long id);
	public Patient createPatient(Patient patient);
	public void updatePatient(Long id,Patient patient);
	public void deletePatient(Long id);

}
