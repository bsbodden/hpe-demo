package com.example.hpedemo;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import com.redis.om.spring.search.stream.EntityStream;
import com.redis.om.spring.search.stream.SearchStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.hpedemo.User$;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableRedisDocumentRepositories
public class HpeDemoApplication {

	Logger logger = LoggerFactory.getLogger(HpeDemoApplication.class);

	@Bean
	CommandLineRunner loadTestData(UserRepository repository, EntityStream es) {
		return args -> {
			String role1name = "role1";
			String role2name = "role2";
			String role3name = "role3";
			String role4name = "role4";

			Role role1 = Role.of();
			role1.setRoles(List.of(role1name, role2name));

			Role role2 = Role.of();
			role2.setRoles(List.of(role3name, role4name));

			User user1 = User.of();
			user1.setUserEmail("user1@test.com");
			user1.setRoles(List.of(role1));
			user1.setProjects(Set.of("project1", "project2"));

			User user2 = User.of();
			user2.setUserEmail("user2@test.com");
			user2.setRoles(List.of(role2));
			user2.setProjects(Set.of("project3", "project4"));

			repository.save(user1);
			repository.save(user2);

			try (SearchStream<User> searchStream = es.of(User.class)) {
				List<User> users = searchStream.filter(User$.USER_EMAIL.eq("user1@test.com")).collect(Collectors.toList());
				users.forEach(u -> logger.info("Found user " + u.getUserEmail()));
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(HpeDemoApplication.class, args);
	}

}
