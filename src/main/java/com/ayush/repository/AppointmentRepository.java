package com.ayush.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayush.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
}
