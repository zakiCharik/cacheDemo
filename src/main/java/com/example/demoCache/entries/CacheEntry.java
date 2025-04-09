package com.example.demoCache.entries;

public class CacheEntry {

	private Object value;
	private long expiryTime;
	
	public CacheEntry(Object value, long ttlMillis) {
		this.value = value;
		this.expiryTime = System.currentTimeMillis() + ttlMillis;
	}

	public boolean isExpired() {
		return System.currentTimeMillis() > this.expiryTime;
	}
	
	public Object getValue() {
		return this.value;
	}
}
