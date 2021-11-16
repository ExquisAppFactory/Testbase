package com.exquis.app.user;

import com.exquis.app.user.entity.RecordStatus;
import com.exquis.app.user.entity.Role;
import com.exquis.app.user.enums.RoleType;
import com.exquis.app.user.enums.StatusType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

	private void seedRole()
	{
		Role role = new Role();
		role.setRole(RoleType.ADMIN);
		role.setDescription("admin role");
	}

	private void seedRecordStatus()
	{
		RecordStatus recordStatus = new RecordStatus();
		recordStatus.setStatus(StatusType.ACTIVE);
		recordStatus.setCreatedAt(LocalDateTime.now());
		recordStatus.setCreatedBy("ebuka@test1.com");

	}

}
