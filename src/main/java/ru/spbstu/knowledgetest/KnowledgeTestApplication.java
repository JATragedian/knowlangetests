package ru.spbstu.knowledgetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class KnowledgeTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowledgeTestApplication.class, args);
	}

}
