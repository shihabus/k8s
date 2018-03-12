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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author predix -
 */
@RestController
public class RedisController {
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/")
	public String test(){
		return "Hi !  Redis Demo Cache";
	}
	 
	
	@RequestMapping("/BNSF/Alliance/tvs/models/cache")
	public String loadcache(){
		
		try {
			
			redisService.loadCache();
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
		return "Success";
		
	}
	
	
	

}
	
 