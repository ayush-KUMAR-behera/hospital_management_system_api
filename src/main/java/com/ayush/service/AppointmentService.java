package com.ayush.service;

import org.springframework.data.domain.Page;

import com.ayush.dto.AppointmentRequestDTO;
import com.ayush.dto.AppointmentResponseDTO;

public interface AppointmentService {

    Page<AppointmentResponseDTO> getAllAppointments(int page, int size, String sortBy);

    AppointmentResponseDTO getAppointmentById(Long id);

    AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto);

    AppointmentResponseDTO updateAppointment(Long appointmentId, AppointmentRequestDTO dto);

    void deleteAppointment(Long id);
}