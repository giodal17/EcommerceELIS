package com.elis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.elis.model.Ruolo;

@Configuration
@EnableWebSecurity
public class GestionePath {

	@Autowired
	AuthenticationProvider provider;
	
	@Autowired
	FilterAutorizzazione filter;
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.headers().frameOptions().disable()
		.and()
			.authorizeHttpRequests()
			.requestMatchers("/consoledb").permitAll()
			.requestMatchers("/All/**").permitAll()
			.requestMatchers("/UtenteAdmin/**").hasRole(Ruolo.ADMIN.getRuoloTrimmed())
			.requestMatchers("/UtenteUser/**").hasRole(Ruolo.USER.getRuoloTrimmed())
			.requestMatchers("/UtenteVenditore/**").hasRole(Ruolo.VENDITORE.getRuoloTrimmed())
			.anyRequest().permitAll()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authenticationProvider(provider)
			.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
}