package com.ayush.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayush.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

}
