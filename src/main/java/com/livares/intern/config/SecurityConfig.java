package com.livares.intern.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {
	
//	@Autowired
//	private JwtAuthFilter authFilter;
//	
//	public UsersDetailsService usersDetailsService() {
//		return new UserInfoService();
//	}

	@Bean
    public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable()).build();
//        		.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
        		//.authorizeHttpRequests(requests -> requests.requestMatchers("/auth/user/**").authenticated())
                //.authorizeHttpRequests(requests -> requests.requestMatchers("/auth/admin/**").authenticated()).sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                
                //.authenticationProvider(null);}



    }
}

