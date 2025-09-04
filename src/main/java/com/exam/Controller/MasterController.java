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
	public ResponseEntity<?> test5(@RequestBody Map<String,Object> body,@RequestHeader("Authorization") String authorizationHeader){
		
		Map<String, Object> paper = new HashMap<>();
        paper.put("examName", "Physics Mid-Term Exam");
        paper.put("examType", "Subject Wise");
        paper.put("examDate", "2025-09-05");
        paper.put("duration", "90 mins");
        paper.put("totalMarks", 100);
        paper.put("totalQs", 6);
        paper.put("exam", "Mid-Term");
        paper.put("subject", "Physics");
        paper.put("chapter", "Mechanics");
        paper.put("inst_name", "ABC Inst");
        paper.put("branch_name", "Kolkata");
        paper.put("subject", "Physics");
        paper.put("created_by", "Subham Mitra");

        // Topics list
        List<Map<String, Object>> topics = new ArrayList<>();

        // Kinematics topic
        Map<String, Object> topic1 = new HashMap<>();
        topic1.put("topic", "Kinematics");

        List<Map<String, Object>> questions1 = new ArrayList<>();

        Map<String, Object> q1 = new HashMap<>();
        q1.put("id", 1);
        q1.put("text", "A car moves with a constant speed of 60 km/h. How far will it travel in 2 hours?");
        q1.put("difficulty", "easy");
        q1.put("options", Arrays.asList(
            Map.of("label", "A", "text", "100 km"),
            Map.of("label", "B", "text", "120 km"),
            Map.of("label", "C", "text", "150 km"),
            Map.of("label", "D", "text", "200 km")
        ));

        Map<String, Object> q2 = new HashMap<>();
        q2.put("id", 2);
        q2.put("text", "An object is dropped from a height of 20 m. Find the time taken to reach the ground.");
        q2.put("difficulty", "medium");
        q2.put("options", Arrays.asList(
            Map.of("label", "A", "text", "1 s"),
            Map.of("label", "B", "text", "2 s"),
            Map.of("label", "C", "text", "3 s"),
            Map.of("label", "D", "text", "4 s")
        ));

        questions1.add(q1);
        questions1.add(q2);
        topic1.put("questions", questions1);
        topics.add(topic1);

        // Dynamics topic
        Map<String, Object> topic2 = new HashMap<>();
        topic2.put("topic", "Dynamics");

        List<Map<String, Object>> questions2 = new ArrayList<>();
        Map<String, Object> q3 = new HashMap<>();
        q3.put("id", 3);
        q3.put("text", "A force of 10 N acts on a mass of 2 kg. What is the acceleration?");
        q3.put("difficulty", "easy");
        q3.put("options", Arrays.asList(
            Map.of("label", "A", "text", "2 m/s²"),
            Map.of("label", "B", "text", "5 m/s²"),
            Map.of("label", "C", "text", "10 m/s²"),
            Map.of("label", "D", "text", "20 m/s²")
        ));
        questions2.add(q3);
        topic2.put("questions", questions2);
        topics.add(topic2);

        paper.put("topics", topics);
			
			return new ResponseEntity<>(Map.of("Message","Success","data",paper),HttpStatus.OK);		

		}
}
