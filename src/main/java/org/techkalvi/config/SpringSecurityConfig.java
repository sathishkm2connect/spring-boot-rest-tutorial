package org.techkalvi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.techkalvi.service.CustomAuthenticationManager;
import org.techkalvi.service.JwtAuthFilter;

import jakarta.servlet.DispatcherType;

@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	CustomAuthenticationManager cam;
	
	@Autowired
	JwtAuthFilter jwtAuthFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(
	             auth -> auth.requestMatchers("/employee/**").hasAuthority("admin")
	            .requestMatchers(HttpMethod.POST,"/login","/register").permitAll()
    			.anyRequest().authenticated()
	           ).httpBasic(Customizer.withDefaults())
				.csrf(AbstractHttpConfigurer::disable)
				.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class)
	 			.authenticationManager(cam).build();

	}
}
