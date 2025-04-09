package com.example.demoCache.services;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.demoCache.entries.CacheEntry;

@Service
public class CacheService {

	private final ConcurrentHashMap<String, CacheEntry> cache = new ConcurrentHashMap<>();
	
	public void put(String key, Object value, long ttlMillis) {
		cache.put(key, new CacheEntry(value, ttlMillis));
	}
	
	public Object get(String key) {
		CacheEntry entry = cache.get(key);
		
		if(entry == null || entry.isExpired()) {
			cache.remove(key);
			return null;
		}
		return entry.getValue();
	}
}
