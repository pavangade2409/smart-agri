package com.agri.agent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "jwt.secret=test-jwt-secret-must-be-at-least-32-characters")
class FarmerAgentApplicationTests {
    @Test void contextLoads() {}
}
