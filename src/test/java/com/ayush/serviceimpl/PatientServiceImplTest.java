package com.ayush.serviceimpl;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ayush.dto.PatientRequestDTO;
import com.ayush.dto.PatientResponseDTO;
import com.ayush.entity.Patient;
import com.ayush.exception.ResourceNotFoundException;
import com.ayush.repository.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

	@Mock
	PatientRepository repo;
	
	@InjectMocks
	PatientServiceImpl service;
	
	// Test 1 - happy path
	@Test
	void getPatientById_shouldReturnPatient_whenPatientExists() {
		
		// ARRANGE
		Patient patient=new Patient();
		patient.setId(1l);
		patient.setAge(22);
		patient.setName("Ayush");
		patient.setGender("Male");
		patient.setDeleted(false);
		
		when(repo.findById(1l)).thenReturn(Optional.of(patient));
		
		// ACT
		PatientResponseDTO result=service.getPatientById(1l);
		
		// ASSERT
		assertEquals("Ayush",result.getName());
		assertEquals(22,result.getAge());
		
	}
	
	
	// TEST 2 -exception case
	@Test
	void getPatientById_shouldThrowException_whenPatientNotFound() {
		
		// ARRANGE 
		when(repo.findById(99l)).thenReturn(Optional.empty());
		
		// ACT + ASSERT
		assertThrows(ResourceNotFoundException.class,()->{
			service.getPatientById(99l);
		});
		
	}
	
	// TEST 3 deleted Patient throw exceptio
		@Test
		void getPatientById_shouldThrowException_whenPatientIsDelets() {
			
		  // ARRANGE
			Patient patient=new Patient();
			patient.setId(1l);
			patient.setAge(22);
			patient.setName("Ayush");
			patient.setGender("Male");
			patient.setDeleted(true);
			
			// ACT
			when(repo.findById(1l)).thenReturn(Optional.of(patient));
			
			// ASSERT
			assertThrows(ResourceNotFoundException.class,()->{
				service.getPatientById(1l);
			});
			
		}
		
		// TEST 4 create Patient sholud return correct
		@Test
		void createPatient_shouldReturn_correctPatient() {
			
			// ARRANGE
			PatientRequestDTO dto=new PatientRequestDTO();
			dto.setName("Ayush");
			dto.setAge(22);
			dto.setGender("Male");
			dto.setEmail("ayush@gmail.com");
			
			Patient p=new Patient();
			p.setId(2l);
			p.setName(dto.getName());
			p.setAge(dto.getAge());
			p.setEmail(dto.getEmail());
			p.setGender(dto.getGender());
			p.setDeleted(false);
			
			when(repo.save(any())).thenReturn(p);
			
			// ACT
			PatientResponseDTO res=service.createPatient(dto);
			
			// ASSERT
			assertEquals(2l,res.getId());
			
		}
		
		// TEST 5-> delete Patient should soft delete Successfully
		
		@Test
		void deletePatient_shouldSoftDelete_whenPatientExists() {
			
			// ARRANGE
			Patient patient=new Patient();
			patient.setId(1l);
			patient.setName("Lucy");
			patient.setDeleted(false);
			
			when(repo.findById(1l)).thenReturn(Optional.of(patient));
			
			// ACT
			service.deletePatient(1l);
			
			// ASSERT
			assertEquals(true,patient.getDeleted());
			verify(repo,times(1)).save(patient);
		}
	
		
	// Test 6 - delete Patient should throw when already deleted
	  
		@Test
		void deletePatient_shouldThrow_whenAlreadyDeleted() {
			
			// ARRANGE
			Patient patient=new Patient();
			patient.setId(1l);
			patient.setDeleted(true); // it means already deleted
			
			when(repo.findById(1l)).thenReturn(Optional.of(patient));
			
			// ACT + ASSERT
			assertThrows(ResourceNotFoundException.class,()->{
				service.deletePatient(1l);
			});
			
		}
	
}
