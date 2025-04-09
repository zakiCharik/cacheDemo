package com.example.demoCache.api.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CacheControllerTest {

	@Autowired
	private MockMvc mockMvc;
	

	@Test
	void testPostGetCache() throws Exception {
		mockMvc.perform(post("/cache")
				.param("key","mKey")
				.param("value","mValue")
				.param("ttl","1000")
				).andExpect(status().isOk());
		
		mockMvc.perform(get("/cache")
				.param("key","mKey")
				).andExpect(content().string("mValue"));
	}
	
	@Test
	void testGetExpiredCache()throws Exception {
		mockMvc.perform(post("/cache")
				.param("key","mKey")
				.param("value","mValue")
				.param("ttl","100")
				).andExpect(status().isOk());
		
		Thread.sleep(100);
		
		mockMvc.perform(get("/cache")
				.param("key","mKey")
				).andExpect(status().isNotFound());
	}
}
