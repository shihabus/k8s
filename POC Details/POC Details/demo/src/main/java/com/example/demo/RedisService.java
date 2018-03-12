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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @author predix -
 */
@Service
public class RedisService {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	JedisConnectionFactory factory;
//	
//	@Value("${tvs.gates.name2}")
//	String gatesFilename;
//	
//	@Value("${tvs.edges.name1}")
//	String edgesFilename;
//	
//	@Value("${tvs.areas.name1}")
//	String areasFilename;
//	
	
	public void post() {
		System.out.println("============================= " + factory.getHostName());
		System.out.println("============================= " + factory.getPort());
		this.stringRedisTemplate.opsForValue().append("ayushi", "aaa");

	}

	public String get() {
		// return (String) this.values.get("amar");
		// return this.jedis.get("events/city/rome/output");
		return (String) this.stringRedisTemplate.opsForValue().get("ayushi");
	}

	public List<Object> getAreas() {
		// return (String) this.values.get("amar");
		// return this.jedis.get("events/city/rome/output");
		return redisTemplate.opsForList().range("relational_areas", 0, 2);
	}

	public List<Object> getLots() {
		// return (String) this.values.get("amar");
		// return this.jedis.get("events/city/rome/output");
		return redisTemplate.opsForList().range("relational_lots", 0, 2);
	}

	public List<Object> getGates() {
		// return (String) this.values.get("amar");
		// return this.jedis.get("events/city/rome/output");
		return redisTemplate.opsForList().range("relational_gates", 0, 2);
	}

	public List<Object> getEdges() {
		// return (String) this.values.get("amar");
		// return this.jedis.get("events/city/rome/output");
		return redisTemplate.opsForList().range("relational_edges", 0, 2);
	}

	public void loadCache() throws Exception {

		// Load data to cahce
//		push("relational_gates", gatesFilename);
//		push("relational_areas", areasFilename);
//		push("relational_edges", edgesFilename);
		
		redisTemplate.opsForList().leftPush("relational_gates", Relational_Gates.VAL);
		redisTemplate.opsForList().leftPush("relational_areas", Relational_Areas.VAL);
		redisTemplate.opsForList().leftPush("relational_edges",  Relational_Edges.VAL);
 
	}

	private void push(String key, String fileName) throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();	
		String filePath = classLoader.getResource(fileName).getFile();	
		File f = new File(classLoader.getResource(fileName).toURI());
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = null;
		StringBuilder b = new StringBuilder("");
		while ((line = br.readLine()) != null) {			
			b.append(line);
			System.out.println("----------------------"+ line);
		}
		
		System.out.println("==========================="+b.toString());
		
		redisTemplate.opsForList().leftPush(key, b.toString());
	}

}
