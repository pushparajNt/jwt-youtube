package com.jwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/*import org.springframework.security.config.annotation.web.configuration.;*/
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.service.JwtService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {
	
	@Lazy
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private UserDetailsService jwtService;
	
	@Lazy
	@Autowired 
	AuthenticationManager authenticationManager;
	
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	        return authConfig.getAuthenticationManager();
	    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		  http.csrf().disable().cors().disable().authorizeHttpRequests().requestMatchers("/authenticate","/registerNewUser").permitAll()
          .anyRequest().authenticated()
          .and()
          .authenticationManager(authenticationManager)
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		  
		  http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
      return http.build();
		
	//return httpSecurity.build();	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	@Lazy
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
	{
		authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
	}
	
	/*
	 * @Bean public WebSecurityCustomizer webSecurityCustomizer() {
	 * 
	 * }
	 */

}
