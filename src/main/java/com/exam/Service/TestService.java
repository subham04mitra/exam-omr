package com.exam.Service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Repositry.TestRepository;


@Service
public class TestService {

	@Autowired
	TestRepository testrepo;
	
	public List<Map<String,Object>> test(){
		
		
		
		return testrepo.test();
	}
}
