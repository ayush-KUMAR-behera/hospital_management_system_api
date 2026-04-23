package com.ayush.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ayush.dto.DoctorRequestDTO;
import com.ayush.dto.DoctorResponseDTO;

public interface DoctorService {
	
	Page<DoctorResponseDTO> getAllDoctors(int page,int size,String sortBy);
	DoctorResponseDTO getDoctorById(Long id);
	DoctorResponseDTO createDoctor(DoctorRequestDTO dto);
	void updateDoctor(Long id,DoctorRequestDTO dto);
	void deleteDoctor(Long id);

}
