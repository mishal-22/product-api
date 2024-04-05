package com.livares.intern.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.livares.intern.service.UsersDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//	@Autowired
//	private JwtAuthFilter authFilter;
//	
	@Autowired
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private UsersDetailsService customUsersDetailsService;

	@Bean
	public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception {

		return http

				.authorizeHttpRequests(registry -> {
					registry.requestMatchers("/home", "/register/**").permitAll();
					registry.requestMatchers("/admin/**").hasRole("ADMIN");
					registry.requestMatchers("/user/**").hasRole("USER");
					registry.anyRequest().authenticated();
				}).csrf(csrf -> csrf.disable())
				.httpBasic(t -> t.authenticationEntryPoint(restAuthenticationEntryPoint)).build();
		

	}

	@Bean
	public UserDetailsService userDetailsService() {
		return customUsersDetailsService;
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUsersDetailsService);
		provider.setPasswordEncoder(bCryptPasswordEncoder());
		return provider;
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails normalUser=User.builder()
//				.username("Nithin")
//				.password("$2a$12$v.uuEs3ZYoljiP.4/IUOpuclyQFypg9cgnNA/Xb3DCaU1KA5zbLF2")
//				.roles("USER")
//				.build();
//		UserDetails adminUser=User.builder()
//				.username("Mishal")
//				.password("$2a$12$v.uuEs3ZYoljiP.4/IUOpuclyQFypg9cgnNA/Xb3DCaU1KA5zbLF2")
//				.roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(normalUser,adminUser);
//	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
