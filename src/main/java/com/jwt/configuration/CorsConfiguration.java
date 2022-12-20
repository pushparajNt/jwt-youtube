package com.jwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

	
	
	public static final String GET="GET";
	public static final String POST="POST";
	public static final String DELETE="DELETE";
	public static final String PUT="PUT";
	
	public WebMvcConfigurer corsConfigurer(CorsRegistry corsRegistry)
	{
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry corsRegistry)
			{
				corsRegistry.addMapping("/**")
				.allowedMethods(GET,POST,PUT,DELETE)
				.allowedHeaders("*")
				.allowedOriginPatterns("*")
				.allowCredentials(true);
			}
		};
	}
}
