package com.ayush.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ayush.entity.Patient;
import com.ayush.repository.PatientRepository;
import com.ayush.service.PatientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {


	private final PatientRepository repo;


	@Override
	public List<Patient> getAllPatient() {
		List<Patient> list=repo.findByDeletedFalse();
		return list;
	}

	@Override
	public Patient getPatientById(Long id) {
		Patient p=repo.findById(id).orElseThrow(()-> new RuntimeException("Patient not found with this id"));
		
		if(p.getDeleted()) {
			throw new RuntimeException("Patient not found");
		}
		return p;
	}

	@Override
	public Patient createPatient(Patient patient) {
	return repo.save(patient);
	}

	@Override
	public void updatePatient(Long id, Patient patient) {
		Patient p=repo.findById(id).orElseThrow(()-> new RuntimeException("Patient not found with this id"));
		if(p.getDeleted()) {
			throw new RuntimeException("Delete Patient can't update ");
		}
		p.setAge(patient.getAge());
		p.setName(patient.getName());
		p.setGender(patient.getGender());
		repo.save(p);
	}

	@Override
	public void deletePatient(Long id) {
		Patient p=repo.findById(id).orElseThrow(()-> new RuntimeException("Patient not found with this id"));
		if(p.getDeleted()) {
			throw new RuntimeException("Patient already deleted");
		}
		p.setDeleted(true);
		repo.save(p);
		
	}

}
