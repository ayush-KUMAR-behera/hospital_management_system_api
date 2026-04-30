package com.ayush.serviceimpl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ayush.dto.AppointmentRequestDTO;
import com.ayush.dto.AppointmentResponseDTO;
import com.ayush.entity.Appointment;
import com.ayush.entity.Doctor;
import com.ayush.entity.Patient;
import com.ayush.exception.ResourceNotFoundException;
import com.ayush.repository.AppointmentRepository;
import com.ayush.repository.DoctorRepository;
import com.ayush.repository.PatientRepository;
import com.ayush.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    private static final Logger logger =
            LoggerFactory.getLogger(AppointmentServiceImpl.class);

    
    @Override
    public Page<AppointmentResponseDTO> getAllAppointments(int page, int size, String sortBy) {
    	
    	logger.info("Fetch All Appoints");

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        Page<Appointment> pageData = repo.findByIsActiveTrue(pageable);

        return pageData.map(this::mapToResponse);
    }

    
    @Override
    public AppointmentResponseDTO getAppointmentById(Long id) {

        logger.info("Fetching appointment with id: {}", id);

        Appointment appointment = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        if (!appointment.isActive()) {
            throw new ResourceNotFoundException("Appointment not found");
        }

        return mapToResponse(appointment);
    }

    
    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto) {

        logger.info("Creating appointment");

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        if (dto.getAppointmentTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment must be in future");
        }

        boolean exists = repo.existsByDoctorAndAppointmentTime(
                doctor, dto.getAppointmentTime());

        if (exists) {
            throw new IllegalArgumentException("Doctor not available at this time");
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentTime(dto.getAppointmentTime());

        Appointment saved = repo.save(appointment);

        return mapToResponse(saved);
    }
    
  
    @Override
    public AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO dto) {

    	logger.info("Updating appointment with id: {}", id);
    	
        Appointment appointment = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        if (!appointment.isActive()) {
            throw new ResourceNotFoundException("Appointment deleted");
        }

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentTime(dto.getAppointmentTime());

        Appointment updated = repo.save(appointment);

        return mapToResponse(updated);
    }

    
    @Override
    public void deleteAppointment(Long id) {

        Appointment appointment = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        if (!appointment.isActive()) {
            throw new ResourceNotFoundException("Already deleted");
        }

        appointment.setActive(false);
        repo.save(appointment);

        logger.info("Appointment soft deleted: {}", id);
    }

   
    private AppointmentResponseDTO mapToResponse(Appointment appointment) {

        AppointmentResponseDTO dto = new AppointmentResponseDTO();

        dto.setAppointmentId(appointment.getId());
        dto.setPatientName(appointment.getPatient().getName());
        dto.setDoctorName(appointment.getDoctor().getName());
        dto.setDoctorSpeciality(appointment.getDoctor().getSpeciality());
        dto.setAppointmentTime(appointment.getAppointmentTime());

        return dto;
    }
}