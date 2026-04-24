package com.ayush.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ayush.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
	List<Doctor> findByDeletedFalse();
	Page<Doctor> findByDeletedFalse(Pageable pageable); 

}
