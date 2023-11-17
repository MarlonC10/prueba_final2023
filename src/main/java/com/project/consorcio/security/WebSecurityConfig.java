package com.project.consorcio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//clase de configuración
@Configuration
//habilitar seguridad
@EnableWebSecurity
public class WebSecurityConfig {
	
	//método para habilitar el proceso de encriptar claves
	@Bean
	public BCryptPasswordEncoder encriptarClave(){
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/sesion/**","resources/**").permitAll()
				.requestMatchers("/medicamento/**","/medico/**","/requerimiento/**")
				.authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/sesion/login")
				.defaultSuccessUrl("/sesion/intranet")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServices();
	}
	
	
	
	
	
	
}
