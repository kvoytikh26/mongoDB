package com.demo;

import com.demo.model.Address;
import com.demo.model.Gender;
import com.demo.model.Student;
import com.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository,
							 MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address(
					"Ukraine",
					"Rivne",
					"some"
			);
			String email = "kris5s@gmail.com";
			Student student1 = new Student(
					"Kristina",
					"Voytikh",
					email,
					Gender.FEMALE,
					address,
					List.of("Math", "Computer SCience"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);

			repository.save(student1);

			Student student2 = new Student(
					"Kristina",
					"Voytikh",
					"kriss4@gmail.com",
					Gender.FEMALE,
					address,
					List.of("Computer"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);
			repository.save(student2);

//			usingMongoTemplateAndQuery(repository, mongoTemplate, email, student1);
			repository.findStudentByEmail(email)
					.ifPresentOrElse(s -> System.out.println(s + " already exists"), () -> {
						System.out.println("Inserting student " + student1);
						repository.insert(student1);
					});
		};
	}

	private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate,
											String email, Student student){
		//--if you don't use repository
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		List<Student> students = mongoTemplate.find(query, Student.class);
		if (students.size() > 1) {
			throw new IllegalStateException("found many students with email "
					+ email);
		}

		if (students.isEmpty()) {
			System.out.println("Inserting student " + student);
			repository.insert(student);
		} else {
			System.out.println(student + " already exists");
		}
	}
}

