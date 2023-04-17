package com.gatewayservice.eurekagateway;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RedisHashComponent {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public RedisHashComponent(RedisTemplate<String, Object> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}
	
	//metoda za stavljanje u redis hes bazu
	public void hSet(String apiKey, Object hashKey, Object apiObject) {
		Map ruleHash = this.objectMapper(apiObject, Map.class);
		redisTemplate.opsForHash().put(apiKey, hashKey, ruleHash);
	}
	//metoda za getovanje iz hesa u redis bazi
	public Object hGet(String key, Object hashKey) {
		return redisTemplate.opsForHash().get(key, hashKey);
	}
	//
	public List<Object> hValues(String key){
		return redisTemplate.opsForHash().values(key);
	}
	public <T> T objectMapper(Object object, Class<T> contentClassType) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(object, contentClassType);
	}
}
