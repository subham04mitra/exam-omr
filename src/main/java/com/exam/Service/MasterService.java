package com.exam.Service;

import java.util.ArrayList;
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
	
	public ResponseEntity<ApiResponses> savePaperService(CommonReqModel model, ResponseBean response, String authToken){
		int data=0;
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
				data=masterrepo.savePaperRepo(uuid,model,"","");
				if(data>0) {
			       
					return response.AppResponse("Success", null, resList);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
}
