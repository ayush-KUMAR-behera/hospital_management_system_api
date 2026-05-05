package com.ayush.config;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ayush.util.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	
	private final JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
	    .csrf(csrf -> csrf.disable())
	    .authorizeHttpRequests(auth -> auth
	    		
	    		//Public ends point Swagger
	    		.requestMatchers(
		                "/api/v1/auth/**",
		                "/v3/api-docs/**",
		                "/swagger-ui/**",
		                "/swagger-ui.html"
		        ).permitAll()

	        // GET → both ADMIN & DOCTOR
	        .requestMatchers(HttpMethod.GET, "/api/v1/patients/**")
	            .hasAnyRole("ADMIN", "DOCTOR")

	        // DELETE → only ADMIN
	        .requestMatchers(HttpMethod.DELETE, "/api/v1/patients/**")
	            .hasRole("ADMIN")

	        // POST → only ADMIN
	        .requestMatchers(HttpMethod.POST, "/api/v1/patients/**")
	            .hasRole("ADMIN")

	        // PUT → only ADMIN
	        .requestMatchers(HttpMethod.PUT, "/api/v1/patients/**")
	            .hasRole("ADMIN")

	         // Doctor end points
	            .requestMatchers(HttpMethod.GET, "/api/v1/doctors/**").hasAnyRole("ADMIN", "DOCTOR")
	            .requestMatchers(HttpMethod.POST, "/api/v1/doctors/**").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.PUT, "/api/v1/doctors/**").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.DELETE, "/api/v1/doctors/**").hasRole("ADMIN")

	            
	            
	         // Appointment endpoints
	            .requestMatchers(HttpMethod.GET, "/api/v1/appointments/**")
	                .hasAnyRole("ADMIN", "DOCTOR")
	                
	            .requestMatchers(HttpMethod.POST, "/api/v1/appointments/**")
	                .hasAnyRole("ADMIN", "DOCTOR")
	                
	            .requestMatchers(HttpMethod.PUT, "/api/v1/appointments/**")
	                .hasAnyRole("ADMIN", "DOCTOR")
	                
	            .requestMatchers(HttpMethod.DELETE, "/api/v1/appointments/**")
	                .hasRole("ADMIN")
	            
	            
	                // Actuator
	                .requestMatchers("/actuator/**").hasRole("ADMIN")
	                
	        .anyRequest().authenticated()
	        
	        
	        
	    )
	    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
	    return username -> {
	        throw new RuntimeException("User not found");
	    };
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}