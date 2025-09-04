package com.exam.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.Exception.GlobalExceptionHandler;
import com.exam.Repositry.MasterRepository;
import com.exam.Response.ApiResponses;
import com.exam.Response.ResponseBean;
import com.exam.Security.TokenService;
import com.exam.reqDTO.CommonReqModel;
import com.exam.resDTO.DashResModel;
import com.exam.resDTO.MasResDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MasterService {

	@Autowired
	MasterRepository masterrepo;
	@Autowired
	TokenService tokenservice;
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
						res.setAvailableQs(0);
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
						res.setAvailableQs(0);
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
						res.setAvailableQs(0);
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
		int data=0;
		List<Map<String,Object>> data2=null;
		List<MasResDTO> resList=new ArrayList<>();
		
		List<Map<String,Object>> qsSet=new ArrayList<>();
		
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
				String pid=randomAlphaNumeric(10);
				model.setPaperid(pid);
				List<Map<String,Object>> topicData=(List<Map<String, Object>>) model.getTopics();
//				ObjectMapper mapper = new ObjectMapper();
//				String topicJson = mapper.writeValueAsString(topicData);
				
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
				        	  			        	  
				        	  Map<String, Object> optnRowA = new HashMap<>();
				        	  optnRowA.put("text", row1.get("option_a"));
				        	  optnRowA.put("label", "A");
				        	  optionSet.add(optnRowA);

				        	  Map<String, Object> optnRowB = new HashMap<>();
				        	  optnRowB.put("text", row1.get("option_b"));
				        	  optnRowB.put("label", "B");
				        	  optionSet.add(optnRowB);

				        	  Map<String, Object> optnRowC = new HashMap<>();
				        	  optnRowC.put("text", row1.get("option_c"));
				        	  optnRowC.put("label", "C");
				        	  optionSet.add(optnRowC);

				        	  Map<String, Object> optnRowD = new HashMap<>();
				        	  optnRowD.put("text", row1.get("option_d"));
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
				System.out.println(qsSet);
				String[] topicDataList = topicData.stream()
					    .map(row -> (String) row.get("topic"))
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
				data=masterrepo.savePaperRepo(uuid,model,topicDataList,"");
				if(data>0) {
			       
					return response.AppResponse("Success", null,pid);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
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
