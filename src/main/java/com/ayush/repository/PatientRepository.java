package com.ayush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayush.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long>{
	List<Patient> findByDeletedFalse();
}
