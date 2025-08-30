package com.exam.Repositry;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	public List<Map<String,Object>> test(){
		
		String query="SELECT x.* FROM public.test_table x";
		return jdbctemplate.queryForList(query);
	}
}
