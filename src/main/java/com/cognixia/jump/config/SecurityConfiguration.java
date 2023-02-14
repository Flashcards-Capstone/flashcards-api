package com.cognixia.jump.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognixia.jump.filter.JwtRequestFilter;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;

	
	// Authentication - who are you?
	@Bean
	protected UserDetailsService userDetailsService() {
		
		return userDetailsService;
	}
	
	// Authorization - what do you want?
	@Bean
	protected SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/authenticate").permitAll()
			.antMatchers("/api/user").permitAll()
			.antMatchers("/api/user/{id}").permitAll()
			//.antMatchers("/api/stack").hasRole("TEACHER")
			.antMatchers("/api/stack").permitAll()
			.antMatchers("/api/stack/{id}").permitAll()
			.antMatchers("/api/stack/stack_id").permitAll()
			.antMatchers("/api/stack/subject/{subject}").permitAll()
			.antMatchers("/api/card").permitAll()
			.antMatchers("/api/login/{username}/{password}").permitAll()
			.antMatchers("/api/login/{username}").permitAll()
			.antMatchers("/api/card/{stack_id}").permitAll()

			.antMatchers("/api/card/{id}").permitAll()
			.antMatchers("/api/card/card_id").permitAll()

			.anyRequest().authenticated() // if not specified, all other end points need a user login
			.and()
			.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ); // tell spring security to NOT CREATE sessions;
		

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
			
		return http.build();
	}
	
	// Encoder -> method that will encode/decode all the user passwords
	@Bean
	protected PasswordEncoder encoder() {
		
		
		// there's many options for password encoding, just pick a algorithm that you like
			return new BCryptPasswordEncoder(); 
	}
	
	// load the encoder & user details service that are needed for spring security to do authentication
	@Bean
	protected DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder( encoder() );
		
		return authProvider;
	}
	
	// can autowire and access the authentication manager (manages authentication (login) of our project)
		@Bean
		protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
			return authConfig.getAuthenticationManager();
		}

}
