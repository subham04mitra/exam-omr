package com.exam.Repositry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.exam.reqDTO.CommonReqModel;

@Repository
public class MasterRepository {


	@Autowired
	NamedParameterJdbcTemplate jdbctemplate;
	
	public List<Map<String,Object>> getSubjectRepo(){
		List<Map<String,Object>> data=null;
		String query="";
		Map<String,Object> params=new HashMap<>();
		try {
			
			query="select x.subject_name,x.sub_id from emsadmin.subject_details x where x.active_yn ='Y'";
			
			
			data=jdbctemplate.queryForList(query, params);
		}catch(Exception ex) {
			throw ex;
		}
		return data;
	}
	
	public List<Map<String,Object>> getChapterRepo(CommonReqModel model){
		List<Map<String,Object>> data=null;
		String query="";
		Map<String,Object> params=new HashMap<>();
		try {			
			query="select x.chapter_name ,x.chap_id from emsadmin.chapter_details x where x.active_yn ='Y' and x.sub_id =:sub_id";
							
			params.put("sub_id", model.getSubject());
			data=jdbctemplate.queryForList(query, params);
		}catch(Exception ex) {
			throw ex;
		}
		return data;
	}

	
	public List<Map<String,Object>> getTopicRepo(CommonReqModel model){
		List<Map<String,Object>> data=null;
		String query="";
		Map<String,Object> params=new HashMap<>();
		try {			
			query="select x.topic_name ,x.topic_id from emsadmin.topic_details x where x.active_yn ='Y' and x.chap_id =:chap_id";
							
			params.put("chap_id", model.getChapter());
			data=jdbctemplate.queryForList(query, params);
		}catch(Exception ex) {
			throw ex;
		}
		return data;
	}
	
	public int savePaperRepo(String uuid,CommonReqModel model,String topic,String pdf){
		int data=0;
		Map<String,Object> params=new HashMap<>();
		String query="";
		try {
//			System.out.println(uuid+jwt);
			query="""
					INSERT INTO masadmin.mas_exam
	(exam_name, created_by, created_at,  ans_id, exam_type, subject, chapter, topic, exam_pdf)
	VALUES(:exam_name, :uuid, now(),  'XX', :exam_type,:subject, :chapter, :topic, :pdf);
					""";
			params.put("uuid", uuid);
			params.put("exam_name", model.getExamName());
			params.put("exam_type", model.getExamType());
			params.put("subject", model.getSubject());
			params.put("chapter", model.getChapter());
			params.put("topic", topic);
			params.put("pdf", pdf);
			data=jdbctemplate.update(query, params);
		}catch(Exception ex) {
			ex.printStackTrace();
				throw ex;
			}
		return data;
	}
}
