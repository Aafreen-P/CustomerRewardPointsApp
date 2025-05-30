package com.telecom.retailreward.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI rewardAPIInfo() {
		return new OpenAPI().info(new Info().title("Reward Program API")
				.description("REST API for calculating reward points").version("1.0"));
	}
}
