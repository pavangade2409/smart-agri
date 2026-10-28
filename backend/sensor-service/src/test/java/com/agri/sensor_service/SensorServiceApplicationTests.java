package com.agri.sensor_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.agri.sensor.SensorServiceApplication;
import com.agri.sensor.entity.NpkMeasurement;
import com.agri.sensor.enums.NpkMeasurementSource;
import com.agri.sensor.repository.NpkMeasurementRepository;
import com.agri.sensor.enums.SoilTestingSeason;
import com.agri.sensor.enums.SoilSamplingStage;
import com.agri.sensor.enums.SoilCropGrowthStage;

import java.time.OffsetDateTime;

@SpringBootTest(classes = SensorServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "jwt.secret=test-jwt-secret-must-be-at-least-32-characters")
class SensorServiceApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private NpkMeasurementRepository npkMeasurements;

	@Test
	void contextLoads() {
	}

	@Test
	void requiresAuthenticationForSensorEndpoints() throws Exception {
		mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders
						.get("/api/v1/sensors"))
				.andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers
						.status().isUnauthorized());
	}

	@Test
	void requiresAuthenticationForNpkEndpoints() throws Exception {
		mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders
						.get("/api/v1/soil-health/farms/1/npk"))
				.andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers
						.status().isUnauthorized());
	}

	@Test
	void persistsNpkMeasurementValuesAndFarmAssociation() {
		NpkMeasurement measurement = new NpkMeasurement();
		measurement.setFarmId(System.currentTimeMillis());
		measurement.setSeason(SoilTestingSeason.RABI);
		measurement.setCropYear(2026);
		measurement.setCropGrowthStage(SoilCropGrowthStage.VEGETATIVE);
		measurement.setSamplingStage(SoilSamplingStage.DURING_CROP_GROWTH);
		measurement.setNitrogen(42.0);
		measurement.setPhosphorus(18.5);
		measurement.setPotassium(27.0);
		measurement.setPhValue(6.4);
		measurement.setEcValue(0.35);
		measurement.setEcUnit("dS/m");
		measurement.setTestingMethod("Olsen");
		measurement.setUnit("mg/kg");
		measurement.setMeasuredAt(OffsetDateTime.now().minusHours(1));
		measurement.setTestDate(OffsetDateTime.now().minusHours(1));
		measurement.setSource(NpkMeasurementSource.LAB_ANALYSIS);
		measurement.setRecordedBy(12L);

		NpkMeasurement saved = npkMeasurements.saveAndFlush(measurement);

		NpkMeasurement loaded = npkMeasurements.findById(saved.getId()).orElseThrow();
		assertEquals(measurement.getFarmId(), loaded.getFarmId());
		assertEquals(42.0, loaded.getNitrogen());
		assertEquals("mg/kg", loaded.getUnit());
		assertEquals(NpkMeasurementSource.LAB_ANALYSIS, loaded.getSource());
		assertEquals(SoilTestingSeason.RABI, loaded.getSeason());
		assertEquals(2026, loaded.getCropYear());
		assertEquals(6.4, loaded.getPhValue());
		assertEquals("Olsen", loaded.getTestingMethod());
	}

}
