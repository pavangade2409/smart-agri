package com.agri.irrigation_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.TestPropertySource;

import com.agri.irrigation.IrrigationServiceApplication;

@SpringBootTest(classes = IrrigationServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "jwt.secret=test-jwt-secret-must-be-at-least-32-characters")
class IrrigationServiceApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void requiresAuthenticationForIrrigationCommands() throws Exception {
		mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders
						.post("/api/v1/irrigation/commands"))
				.andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers
						.status().isUnauthorized());
	}

}
