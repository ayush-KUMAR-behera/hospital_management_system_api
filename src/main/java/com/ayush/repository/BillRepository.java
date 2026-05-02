package com.ayush.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayush.entity.Appointment;
import com.ayush.entity.Bill;

public interface BillRepository extends JpaRepository<Bill,Long> {
	boolean existsByAppointment(Appointment appointment);
}
