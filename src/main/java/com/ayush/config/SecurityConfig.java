package com.ayush.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ayush.util.JwtFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;
	
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
	    		

	        // OPEN
	        .requestMatchers("/api/v1/auth/**").permitAll()

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