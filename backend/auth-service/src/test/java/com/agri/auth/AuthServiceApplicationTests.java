package com.agri.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
	properties = {
		"spring.datasource.url=jdbc:h2:mem:auth-test;DB_CLOSE_DELAY=-1",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.hibernate.ddl-auto=create-drop",
		"jwt.secret=local-test-secret-with-at-least-32-characters"
	}
)
class AuthServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
