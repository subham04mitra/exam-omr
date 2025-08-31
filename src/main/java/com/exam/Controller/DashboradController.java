package com.exam.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Response.ApiResponses;
import com.exam.Response.ResponseBean;
import com.exam.Service.DashboardService;

@RestController
@RequestMapping("/api/dash")
public class DashboradController {

	@Autowired
	DashboardService dahserv;

	ResponseBean responseBean=new ResponseBean();
	
	
	@GetMapping
	public ResponseEntity<ApiResponses> refreshTokenController(@RequestHeader("Authorization") String authorizationHeader){
		String authToken = authorizationHeader.split(" ")[1];
		ResponseEntity<ApiResponses> finalResponse;
		
		finalResponse=dahserv.dashboardDynamicService(responseBean,authToken);
		
		return finalResponse;
	}
	
	
}
