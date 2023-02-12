package ru.spbstu.knowledgetest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableMongoRepositories
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Knowledge testing server URL")})
public class KnowledgeTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowledgeTestApplication.class, args);
	}

}
