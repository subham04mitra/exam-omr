package com.exam.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Service.TestService;

@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	TestService testserv;
	@GetMapping
	public ResponseEntity<?> test(){
		
		List<Map<String,Object>> data=testserv.test();
		
		return new ResponseEntity<>(Map.of("Message","Success","Data",data),HttpStatus.OK);
		









		//TEST
	}
	
	
	@GetMapping("/a")
	public ResponseEntity<?> test2(){
		
		List<Map<String,Object>> data=testserv.test();
		
		return new ResponseEntity<>(Map.of("Message","Success","Data",data),HttpStatus.OK);
		

	}
}
