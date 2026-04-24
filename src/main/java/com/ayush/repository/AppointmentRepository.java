package com.ayush.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ayush.entity.Appointment;
import com.ayush.entity.Doctor;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorAndAppointmentTime(Doctor doctor, LocalDateTime appointmentTime);

    Page<Appointment> findByIsActiveTrue(Pageable pageable);
}