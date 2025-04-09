package com.example.demoCache.api.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoCache.services.CacheService;

@RestController
@RequestMapping("/cache")
public class CacheController {

	@Autowired
	private CacheService cacheService;
	
	@PostMapping
	public ResponseEntity<Void> put(@RequestParam String key, @RequestParam String value, @RequestParam long ttl ){
		cacheService.put(key, value, ttl);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<Object> get(@RequestParam String key){
		Object value = cacheService.get(key);		
		return value != null ? ResponseEntity.ok(value) : ResponseEntity.notFound().build();
	}
}
