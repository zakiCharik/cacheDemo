package com.example.demoCache.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CacheServiceTest {
	
	private CacheService cacheService;
	
	@BeforeEach
	void setUp() {
		cacheService = new CacheService();
	}
	
	@Test
	void testPutAndGetValueOk() {
		cacheService.put("key", "value", 1000);
		Object value = cacheService.get("key");
		assertEquals("value", value);
	}
	
	@Test
	void testExiredValue() throws InterruptedException {
		cacheService.put("key", "value", 500);
		Thread.sleep(500);
		Object value = cacheService.get("key");
		assertNull(value, "Must be null value");
	}
}
