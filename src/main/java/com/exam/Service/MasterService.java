package com.exam.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exam.Exception.GlobalExceptionHandler;
import com.exam.Repositry.MasterRepository;
import com.exam.Response.ApiResponses;
import com.exam.Response.ResponseBean;
import com.exam.Security.TokenService;
import com.exam.Util.OmrScanner;
import com.exam.reqDTO.CommonReqModel;
import com.exam.resDTO.DashResModel;
import com.exam.resDTO.MasResDTO;
import com.exam.resDTO.QsPaperListModel;
import com.exam.resDTO.StudentDataModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MasterService {

	@Autowired
	MasterRepository masterrepo;
	@Autowired
	TokenService tokenservice;
	@Autowired
	OmrScanner omrscan;
	public ResponseEntity<ApiResponses> getSubjectService(ResponseBean response, String authToken){
		List<Map<String,Object>> data=null;
		List<MasResDTO> resList=new ArrayList<>();
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
				String[] tdata=tokenservice.decodeJWT(authToken);
				String uuid=tdata[1];
				String role=tdata[0];
//				System.out.println(role);
				data=masterrepo.getSubjectRepo();
				if(!data.isEmpty() && data!=null) {
			        for (Map<String, Object> row : data) {
						MasResDTO res=new MasResDTO();
						res.setName(row.get("subject_name").toString());
						res.setId(row.get("sub_id").toString());
						List<Map<String,Object>> data2=masterrepo.getavlQsRepo(row.get("sub_id").toString());
						res.setAvailableQs(data2);
						resList.add(res);
					}
					
					return response.AppResponse("Success", null, resList);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public ResponseEntity<ApiResponses> getExamTypeService(ResponseBean response, String authToken){
		List<Map<String,Object>> data=null;
		List<MasResDTO> resList=new ArrayList<>();
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
				String[] tdata=tokenservice.decodeJWT(authToken);
				String uuid=tdata[1];
				String role=tdata[0];
//				System.out.println(role);
				data=masterrepo.getExamTypeRepo();
				if(!data.isEmpty() && data!=null) {
			        for (Map<String, Object> row : data) {
						MasResDTO res=new MasResDTO();
						res.setName(row.get("pattern_type").toString());
						res.setId(row.get("pat_id").toString());
						resList.add(res);
					}
					
					return response.AppResponse("Success", null, resList);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public ResponseEntity<ApiResponses> getExamNameService(ResponseBean response, String authToken){
		List<Map<String,Object>> data=null;
		List<MasResDTO> resList=new ArrayList<>();
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
				String[] tdata=tokenservice.decodeJWT(authToken);
				String uuid=tdata[1];
				String role=tdata[0];
//				System.out.println(role);
				data=masterrepo.getExamNameRepo();
				if(!data.isEmpty() && data!=null) {
			        for (Map<String, Object> row : data) {
						MasResDTO res=new MasResDTO();
						res.setName(row.get("exam_name").toString());
						res.setId(row.get("exam_id").toString());
						resList.add(res);
			        }
					
					return response.AppResponse("Success", null, resList);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	
	
	public ResponseEntity<ApiResponses> getChapterService(CommonReqModel model, ResponseBean response, String authToken){
		List<Map<String,Object>> data=null;
		List<MasResDTO> resList=new ArrayList<>();
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
				String[] tdata=tokenservice.decodeJWT(authToken);
				String uuid=tdata[1];
				String role=tdata[0];
//				System.out.println(role);
				data=masterrepo.getChapterRepo(model);
				if(!data.isEmpty() && data!=null) {
			        for (Map<String, Object> row : data) {
						MasResDTO res=new MasResDTO();
						res.setName(row.get("chapter_name").toString());
						res.setId(row.get("chap_id").toString());
						List<Map<String,Object>> data2=masterrepo.getavlQsRepo(row.get("chap_id").toString());
						res.setAvailableQs(data2);
						resList.add(res);
					}
					
					return response.AppResponse("Success", null, resList);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public ResponseEntity<ApiResponses> getTopicService(CommonReqModel model, ResponseBean response, String authToken){
		List<Map<String,Object>> data=null;
		List<MasResDTO> resList=new ArrayList<>();
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
				String[] tdata=tokenservice.decodeJWT(authToken);
				String uuid=tdata[1];
				String role=tdata[0];
//				System.out.println(role);
				data=masterrepo.getTopicRepo(model);
				if(!data.isEmpty() && data!=null) {
			        for (Map<String, Object> row : data) {
						MasResDTO res=new MasResDTO();
						res.setName(row.get("topic_name").toString());
						res.setId(row.get("topic_id").toString());
						List<Map<String,Object>> data2=masterrepo.getavlQsRepo(row.get("topic_id").toString());
						res.setAvailableQs(data2);
						resList.add(res);
					}
					
					return response.AppResponse("Success", null, resList);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public ResponseEntity<ApiResponses> savePaperService(CommonReqModel model, ResponseBean response, String authToken) throws Exception{
		int data=0,data6=0;
		List<Map<String,Object>> data2=null,data3=null,data4=null,data5=null;
		List<MasResDTO> resList=new ArrayList<>();
		Map<String,Object> qsMainSet=new HashMap();
		List<Map<String,Object>> qsSet=new ArrayList<>();
		List<String> ansSet=new ArrayList<>();
		
		Map<String,Object> mainqsData=new HashMap<>();
		int qsid=1;
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
				String[] tdata=tokenservice.decodeJWT(authToken);
				String uuid=tdata[1];
				String role=tdata[0];
				data4=masterrepo.getUserDataRepo(uuid);
				data3=masterrepo.getExamNameRepo(model.getExam());
				data5=masterrepo.getexamTypeDataRepo(model.getExamType());
				String pid=randomAlphaNumeric(10);
				model.setPaperid(pid);
				List<Map<String,Object>> topicData=(List<Map<String, Object>>) model.getTopics();
//				
				
				List<String> topicDataListraw=new ArrayList<>();
				for (Map<String,Object> row : topicData) {
				    String topicId = (String) row.get("topic");
				    int count=Integer.parseInt((String) row.get("count")) ;
				   String type=row.get("type").toString();
				    
				    
				            // now you have all 3 params: topicId, type, count
				          data2 = masterrepo.getQuestionsRepo(topicId, type, count);
//				          System.out.println(data2);
				          
				          for (Map<String,Object> row1 : data2) {
				        	List<Map<String,Object>> optionSet=new ArrayList<>();
							optionSet.clear();
				        	  ansSet.add((String) row1.get("correct_ans"));  
				        	  Map<String, Object> optnRowA = new HashMap<>();
				        	  optnRowA.put("text", row1.get("option_a"));
				        	  optnRowA.put("label", "A");
				        	  optnRowA.put("type", row1.get("option_a_type"));
				        	  optionSet.add(optnRowA);

				        	  Map<String, Object> optnRowB = new HashMap<>();
				        	  optnRowB.put("text", row1.get("option_b"));
				        	  optnRowB.put("label", "B");
				        	  optnRowB.put("type", row1.get("option_b_type"));
				        	  optionSet.add(optnRowB);

				        	  Map<String, Object> optnRowC = new HashMap<>();
				        	  optnRowC.put("text", row1.get("option_c"));
				        	  optnRowC.put("label", "C");
				        	  optnRowC.put("type", row1.get("option_c_type"));
				        	  optionSet.add(optnRowC);

				        	  Map<String, Object> optnRowD = new HashMap<>();
				        	  optnRowD.put("text", row1.get("option_d"));
				        	  optnRowD.put("type", row1.get("option_d_type"));
				        	  optnRowD.put("label", "D");
				        	  optionSet.add(optnRowD);
				        	  
				        	  Map<String,Object> qsRow=new HashMap<>();
				        	  qsRow.put("text", row1.get("question"));
				        	  qsRow.put("id", qsid);
				        	  qsRow.put("options", optionSet);
				        	  
				        	  qsSet.add(qsRow);
				        	  
				        	  qsid++;
						}
//				       
				}
				
				qsMainSet.put("examName", model.getExamName());
				qsMainSet.put("examType", data5.get(0).get("pattern_type"));
				qsMainSet.put("totalQs", model.getTotalQs());
				qsMainSet.put("duration", model.getDuration());
				qsMainSet.put("totalMarks",model.getTotalMarks());
				qsMainSet.put("inst_name", data4.get(0).get("user_inst"));
				qsMainSet.put("branch_name", data4.get(0).get("user_branch"));
				qsMainSet.put("examDate", model.getExamDate());
				qsMainSet.put("created_by", data4.get(0).get("user_name"));
				qsMainSet.put("exam", data3.get(0).get("exam_name"));
				qsMainSet.put("questions", qsSet);

				ObjectMapper mapper = new ObjectMapper();
				String paperJson = mapper.writeValueAsString(qsMainSet);
//				.out.println(qsMainSet);
				String[] topicDataList = topicData.stream()
					    .map(row -> (String) row.get("topic"))
					    .filter(Objects::nonNull)   // optional, in case of nulls
					    .distinct()
					    .toArray(String[]::new);


//				System.out.println("----"+model.getSubject()+"----");
				
				if("".equals(model.getChapter()) & !"".equals(model.getSubject())) {
					String[] subjects = new String[] { (String) model.getSubject() };
					model.setSubject(subjects);
					model.setChapter(topicDataList);
					model.setTopics(new String[0]);

					model.setChapter(topicDataList);
				}
				else if("".equals(model.getSubject()) & "".equals(model.getChapter())) {
					model.setChapter(new String[0]);
					model.setTopics(new String[0]);
					model.setSubject(topicDataList);
				}
				else {
					String[] subjects = new String[] { (String) model.getSubject() };
					model.setSubject(subjects);
					String[] chapters = new String[] { (String) model.getChapter() };
					model.setChapter(chapters);
					model.setTopics(topicDataList);
				}
				data=masterrepo.savePaperRepo(uuid,model,topicDataList,paperJson);
				String[] ansArray = ansSet.stream().toArray(String[]::new);
				data6=masterrepo.saveAnsSheetRepo(uuid, ansArray, pid);
				if(data>0 & data6>0) {
					
					return response.AppResponse("Success", null,pid);
				}
				else {
					return response.AppResponse("Error", null, null);
				}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public ResponseEntity<ApiResponses> getQsPaperService(CommonReqModel model, ResponseBean response, String authToken) throws Exception{
		List<Map<String,Object>> data=null;
		List<MasResDTO> resList=new ArrayList<>();
		String rawJson="";
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
				
//				System.out.println(role);
				data=masterrepo.getQsPaperRepo(model.getPaper_id());
				if(!data.isEmpty() && data!=null) {
			       rawJson=data.get(0).get("paper_pdf").toString();
			       ObjectMapper mapper = new ObjectMapper();
			       JsonNode jsonNode = mapper.readTree(rawJson); // parse string back into JSON

			       Map<String, Object> responseMap = new HashMap<>();
			       responseMap.put("data", jsonNode);
					return response.AppResponse("Success", null, responseMap);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public ResponseEntity<ApiResponses> getQsPaperListService( ResponseBean response, String authToken) throws Exception{
		List<Map<String,Object>> data=null;
		List<QsPaperListModel> resList=new ArrayList<>();
//		String rawJson="";
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
			String[] tdata=tokenservice.decodeJWT(authToken);
			String uuid=tdata[1];
			String role=tdata[0];
//				System.out.println(role);
				data=masterrepo.getQsPaperListRepo(uuid,role);
				if(!data.isEmpty() && data!=null) {
					for (Map<String, Object> row : data) {
						QsPaperListModel paper=new QsPaperListModel();
						paper.setPaper_id(row.get("paper_id").toString());
						paper.setPaper_name(row.get("paper_name").toString());
						paper.setExam_name(row.get("exam_name").toString());
						paper.setExam_type(row.get("pattern_type").toString());
						paper.setUser_name(row.get("user_name").toString());
						paper.setBranch(row.get("user_branch").toString());
						Array subjectArray = (Array) row.get("subjects");
						if (subjectArray != null) {
						    paper.setSubject(Arrays.asList((String[]) subjectArray.getArray()));
						} else {
						    paper.setSubject(Collections.emptyList());
						}

						Array chapterArray = (Array) row.get("chapters");
						if (chapterArray != null) {
						    paper.setChapter(Arrays.asList((String[]) chapterArray.getArray()));
						} else {
						    paper.setChapter(Collections.emptyList());
						}

						Array topicArray = (Array) row.get("topics");
						if (topicArray != null) {
						    paper.setTopic(Arrays.asList((String[]) topicArray.getArray()));
						} else {
						    paper.setTopic(Collections.emptyList());
						}

						paper.setTot_mrks((int) row.get("total_ques"));
						paper.setTot_qs((int) row.get("total_marks"));
						paper.setParer_duration((int) row.get("paper_duration"));
						resList.add(paper);
					}
					
					
					return response.AppResponse("Success", null, resList);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	
	public ResponseEntity<ApiResponses> saveUserService(CommonReqModel model,ResponseBean response, String authToken){
		List<Map<String,Object>> data=null;
		int data2=0;
		List<MasResDTO> resList=new ArrayList<>();
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
				String[] tdata=tokenservice.decodeJWT(authToken);
				String uuid=tdata[1];
				String role=tdata[0];
//				System.out.println(role);
				data=masterrepo.getUserDataRepo(uuid);
//				model.setBranch((String) data.get(0).get("user_branch"));
				model.setInst((String) data.get(0).get("user_inst"));
				if("admin".equalsIgnoreCase(role)) {
					if("owner".equalsIgnoreCase(model.getRole())) {
						data2=masterrepo.saveUserRepo("",model,uuid,uuid);
					}
					else if("teacher".equalsIgnoreCase(model.getRole())) {
						data2=masterrepo.saveUserRepo(model.getOwner(),model,uuid,uuid);
					}
					else {
						return response.AppResponse("Unauothorize", null, null);
					}
					
				}
				else if("owner".equalsIgnoreCase(role)) {
					if("owner".equalsIgnoreCase(model.getRole())) {
						data2=masterrepo.saveUserRepo(uuid,model,data.get(0).get("admin_id").toString(),uuid);
					}
					else {
						return response.AppResponse("Unauothorize", null, null);
					}
					
				}
				else {
					return response.AppResponse("Unauothorize", null, null);
				}
				
				if(!data.isEmpty() && data!=null) {
			       
					
					return response.AppResponse("Success", null, resList);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public ResponseEntity<ApiResponses> getOwnerService(ResponseBean response, String authToken){
		List<Map<String,Object>> data=null;
		List<MasResDTO> resList=new ArrayList<>();
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
				String[] tdata=tokenservice.decodeJWT(authToken);
				String uuid=tdata[1];
				String role=tdata[0];
//				System.out.println(role);
				if("admin".equalsIgnoreCase(role)) {
					data=masterrepo.getOwnerDataRepo(uuid);
				}
				else {
					return response.AppResponse("Unauothorize", null, null);
				}
				
				if(!data.isEmpty() && data!=null) {
			        for (Map<String, Object> row : data) {
						MasResDTO res=new MasResDTO();
						res.setName(row.get("user_name").toString());
						res.setId(row.get("uuid").toString());
						res.setBranch(row.get("user_branch").toString());
						
						resList.add(res);
					}
					
					return response.AppResponse("Success", null, resList);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	
	public ResponseEntity<ApiResponses> checkUserIdService(CommonReqModel model,ResponseBean response, String authToken){
		List<Map<String,Object>> data=null;
		int data2=0;
		List<MasResDTO> resList=new ArrayList<>();
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
				String[] tdata=tokenservice.decodeJWT(authToken);
				String uuid=tdata[1];
				String role=tdata[0];
//				System.out.println(role);
				data=masterrepo.checkUserIdRepo(model.getId());
//				
				if(!data.isEmpty() && data!=null) {
			       
					
					return response.AppResponse("Notfound", null, null);
				}
				else {
					return response.AppResponse("Success", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public ResponseEntity<ApiResponses> checkTokenService(ResponseBean response, String authToken){
		List<Map<String,Object>> data=null;
		List<MasResDTO> resList=new ArrayList<>();
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
				
					
					return response.AppResponse("Success", null, null);
			
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	
	public ResponseEntity<ApiResponses> getStudentDataService(CommonReqModel model ,ResponseBean response, String authToken) throws Exception{
		List<Map<String,Object>> data=null;
		List<StudentDataModel> resList=new ArrayList<>();
//		String rawJson="";
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
			String[] tdata=tokenservice.decodeJWT(authToken);
			String uuid=tdata[1];
			String role=tdata[0];
//				System.out.println(role);
				data=masterrepo.getStudentDataRepo(model.getPaper_id());
				if(!data.isEmpty() && data!=null) {
					for (Map<String, Object> row : data) {
						StudentDataModel student=new StudentDataModel();
						student.setStudent_name(row.get("student_name").toString());
						student.setStudent_roll(row.get("student_id").toString());
						student.setTot_qs((int) row.get("total_ques"));
						student.setTot_marks((int) row.get("total_marks"));
						student.setTot_attm((int) row.get("tot_attempt"));
						student.setTot_crct((int) row.get("tot_correct"));
						student.setTot_wrng((int) row.get("tot_wrong"));
						int crctMark=(int) row.get("tot_correct")*(int) row.get("each_mrk");
						System.out.println(crctMark);
						int wrngMrk=(int) ((int) row.get("tot_wrong")*0.33);
						System.out.println(wrngMrk);
						student.setMrk_obtn(crctMark-wrngMrk);
						resList.add(student);
					}
					
					
					return response.AppResponse("Success", null, resList);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	
	
	public ResponseEntity<ApiResponses> getQsPaperListbycndService(CommonReqModel model, ResponseBean response, String authToken) throws Exception{
		List<Map<String,Object>> data=null;
		List<QsPaperListModel> resList=new ArrayList<>();
//		String rawJson="";
		try {
			if(authToken.isBlank() || authToken.isEmpty()) {
				return response.AppResponse("Nulltype", null, null);
			}
			
			if(!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}
			String[] tdata=tokenservice.decodeJWT(authToken);
			String uuid=tdata[1];
			String role=tdata[0];
//				System.out.println(role);
				data=masterrepo.getQsPaperListbycndRepo(uuid,role,model);
				if(!data.isEmpty() && data!=null) {
					for (Map<String, Object> row : data) {
						QsPaperListModel paper=new QsPaperListModel();
						paper.setPaper_name(row.get("paper_name").toString());
						paper.setPaper_id(row.get("paper_id").toString());
						paper.setExam_date((Date) row.get("exam_date"));
						paper.setUser_name(row.get("user_name").toString());
						paper.setBranch(row.get("user_branch").toString());
						resList.add(paper);
					}
					
					
					return response.AppResponse("Success", null, resList);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	
	public ResponseEntity<ApiResponses> uploadOmrService(MultipartFile file, ResponseBean response, String authToken) throws Exception {
		try {
			if (authToken == null || authToken.isBlank()) {
				return response.AppResponse("Nulltype", null, null);
			}

			if (!tokenservice.validateTokenAndReturnBool(authToken)) {
				throw new GlobalExceptionHandler.ExpiredException();
			}

	
			if (file == null || file.isEmpty()) {
				return response.AppResponse("FileMissing", null, null);
			}

			// Save to temp location
			Path tempFile = Files.createTempFile("omr-", "-" + file.getOriginalFilename());
			Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);


			Map<Integer, String> studentAnswers = omrscan.scan(tempFile.toFile());

			//  Get correct answers from DB



			int score = 0;
			Map<String, Object> resultMap = new HashMap<>();

//			for (Map.Entry<Integer, String> entry : correctAnswers.entrySet()) {
//				String studentAns = studentAnswers.get(entry.getKey());
//				if (entry.getValue().equalsIgnoreCase(studentAns)) {
//					score++;
//				}
//			}
			System.out.println("score"+ score);
			System.out.println("studentAnswers"+ studentAnswers);
			resultMap.put("score", score);
			resultMap.put("studentAnswers", studentAnswers);

			Files.deleteIfExists(tempFile);


			return response.AppResponse("Success", null, resultMap);

		} catch (Exception ex) {
			throw ex; // Will be handled by GlobalExceptionHandler
		}
	}
	
	
	private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String randomAlphaNumeric(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(ALPHA_NUMERIC.length());
            sb.append(ALPHA_NUMERIC.charAt(index));
        }
        return sb.toString();
    }
}
