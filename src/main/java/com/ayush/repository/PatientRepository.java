package com.ayush.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ayush.dto.PatientResponseDTO;
import com.ayush.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long>{
//	List<Patient> findByDeletedFalse();
	Page<Patient> findByDeletedFalse(Pageable pageable);
	Optional<Patient> findByEmail(String email);
}
