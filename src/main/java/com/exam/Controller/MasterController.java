package com.exam.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Repositry.MasterRepository;
import com.exam.Response.ApiResponses;
import com.exam.Response.ResponseBean;
import com.exam.Service.MasterService;
import com.exam.reqDTO.CommonReqModel;

@RestController
@RequestMapping("/api/mas")
public class MasterController {
	@Autowired
	MasterService masservice;
	ResponseBean responseBean=new ResponseBean();
	@GetMapping("/exam-type")
public ResponseEntity<?> getexamType(@RequestHeader("Authorization") String authorizationHeader){
		
//		return new ResponseEntity<>(Map.of("Message","Success","data",List.of("Full Exam", "Subject Wise", "Chapter Wise")),HttpStatus.OK);		
		String authToken = authorizationHeader.split(" ")[1];
		ResponseEntity<ApiResponses> finalResponse;
		
		finalResponse=masservice.getExamTypeService(responseBean,authToken);
		
		return finalResponse;
	}
	
	@GetMapping("/exam-name")
	public ResponseEntity<?> getexamName(@RequestHeader("Authorization") String authorizationHeader){
			
//			return new ResponseEntity<>(Map.of("Message","Success","data",List.of("Full Exam", "Subject Wise", "Chapter Wise")),HttpStatus.OK);		
			String authToken = authorizationHeader.split(" ")[1];
			ResponseEntity<ApiResponses> finalResponse;
			
			finalResponse=masservice.getExamNameService(responseBean,authToken);
			
			return finalResponse;
		}
	
	@GetMapping("/subjects")
	public ResponseEntity<?> getSubject(@RequestHeader("Authorization") String authorizationHeader){
			
		String authToken = authorizationHeader.split(" ")[1];
		ResponseEntity<ApiResponses> finalResponse;
		
		finalResponse=masservice.getSubjectService(responseBean,authToken);
		
		return finalResponse;		

		}
	
	@PostMapping("/chapters")
	public ResponseEntity<?> getChapter(@RequestBody CommonReqModel model,@RequestHeader("Authorization") String authorizationHeader){
			
		String authToken = authorizationHeader.split(" ")[1];
		ResponseEntity<ApiResponses> finalResponse;
		
		finalResponse=masservice.getChapterService(model,responseBean,authToken);
		
		return finalResponse;	

		}
	@PostMapping("/topics")
	public ResponseEntity<?> getTopic(@RequestBody CommonReqModel model,@RequestHeader("Authorization") String authorizationHeader){
			
		String authToken = authorizationHeader.split(" ")[1];
		ResponseEntity<ApiResponses> finalResponse;
		
		finalResponse=masservice.getTopicService(model,responseBean,authToken);
		
		return finalResponse;		

		}
	@PostMapping("/save-paper")
	public ResponseEntity<?> savePaper(@RequestBody CommonReqModel model,@RequestHeader("Authorization") String authorizationHeader) throws Exception{
			
		String authToken = authorizationHeader.split(" ")[1];
		ResponseEntity<ApiResponses> finalResponse;
		
		finalResponse=masservice.savePaperService(model,responseBean,authToken);
		
		return finalResponse;		

		}
	
	@PostMapping("/get-paper")
	public ResponseEntity<?> getPaper(@RequestBody CommonReqModel model,@RequestHeader("Authorization") String authorizationHeader) throws Exception{
		String authToken = authorizationHeader.split(" ")[1];
		ResponseEntity<ApiResponses> finalResponse;
		
		finalResponse=masservice.getQsPaperService(model,responseBean,authToken);
		
		return finalResponse;		

		
		}
}
