/*
 * Copyright (c) 2018 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 
 * @author predix -
 */
@Configuration
public class RedisConfig {

	private @Value("#{systemEnvironment['REDIS_SERVICE_PORT_6379_TCP_ADDR']}") String redisHostName;
//	private @Value("${spring.redis.port}") int redisPort;
	

	@Bean
	public RedisConnectionFactory redisConnectionFactory() throws Exception {
		
//		URI uri = new URI(System.getenv("REDIS_SERVICE_PORT")); 
//		
//		 
//		System.out.println("REDIS HOST NAME : " + uri.getHost());
//		System.out.println("REDIS PORT : " + uri.getPort());
//		
//		Map<String,String> env = System.getenv();
//		for (String propName : env.keySet()) {
//			
//			
//			System.out.println(propName  + " : " +  env.get(propName));
//			
//		}
//		
//		
//		System.out.println(System.getenv("REDIS_SERVICE_PORT_6379_TCP_ADDR"));
 	  System.out.println(redisHostName);
		JedisConnectionFactory factory = new JedisConnectionFactory();
		//factory.setHostName("10.104.252.250");		
		factory.setHostName(redisHostName);
		factory.setPort(6379);
		return factory;
	}

	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)
			throws Exception {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory());
		return template;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() throws Exception {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

}
