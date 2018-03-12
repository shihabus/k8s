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
 

	public void loadCache() throws Exception {
 
		redisTemplate.opsForList().leftPush("relational_gates", Relational_Gates.VAL);
		redisTemplate.opsForList().leftPush("relational_areas", Relational_Areas.VAL);
		redisTemplate.opsForList().leftPush("relational_edges",  Relational_Edges.VAL);
 
	}
 

}
