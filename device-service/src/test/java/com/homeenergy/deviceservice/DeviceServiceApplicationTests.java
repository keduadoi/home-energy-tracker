package com.homeenergy.deviceservice;

import com.homeenergy.deviceservice.entity.Device;
import com.homeenergy.deviceservice.entity.DeviceType;
import com.homeenergy.deviceservice.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DeviceServiceApplicationTests {

	public static final int NUMBER_OF_DEVICES = 200;
	public static final int NUMBER_OF_USERS = 10;

	@Autowired
	private DeviceRepository deviceRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Disabled
	void createDevices() {
		for (int i = 1; i <= NUMBER_OF_DEVICES; i++) {
			var device = Device.builder()
					.name("Device" + i).
					type(DeviceType.values()[i % DeviceType.values().length])
					.location("Location" + ((i % 3) + 1))
					.userId((long) ((i % NUMBER_OF_USERS) + 1))
					.build();

			deviceRepository.save(device);
		}

		log.info("Devices created");
	}
}
