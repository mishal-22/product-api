package com.livares.intern.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

	@Value("${liavres.openapi.dev-url}")
	private String devUrl;
	
	 @Value("${livares.openapi.prod-url}")
	  private String prodUrl;
	 
	 @Bean
	 public OpenAPI myOpenAPI() {
		 Server devServer=new Server();
		 devServer.setUrl(devUrl);
		 devServer.setDescription("Server URL in Development environment");

		    Server prodServer = new Server();
		    prodServer.setUrl(prodUrl);
		    prodServer.setDescription("Server URL in Production environment");

		    Contact contact = new Contact();
		    contact.setEmail("mishal123@gmail.com");
		    contact.setName("Mishal k");
		    contact.setUrl("https://www.mishal123.com");

		    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		    Info info = new Info()
		        .title("Product API")
		        .version("1.0")
		        .contact(contact)
		        .description("This API exposes endpoints to products.").termsOfService("https://www.livares.com/terms")
		        .license(mitLicense);

		    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
		  
	 }
}
