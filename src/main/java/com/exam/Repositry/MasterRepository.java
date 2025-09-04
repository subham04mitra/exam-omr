package com.exam.Repositry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	public List<Map<String,Object>> getExamTypeRepo(){
		List<Map<String,Object>> data=null;
		String query="";
		Map<String,Object> params=new HashMap<>();
		try {
			
			query="select x.pattern_type,x.pat_id from masadmin.mas_exam_pattern x where x.active_yn ='Y'";
			
			
			data=jdbctemplate.queryForList(query, params);
		}catch(Exception ex) {
			throw ex;
		}
		return data;
	}
	
	public List<Map<String,Object>> getExamNameRepo(){
		List<Map<String,Object>> data=null;
		String query="";
		Map<String,Object> params=new HashMap<>();
		try {
			
			query="select x.exam_name,x.exam_id from masadmin.mas_exam1 x where x.active_yn ='Y'";
			
			
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
	@Transactional(rollbackFor = Exception.class)
	public int savePaperRepo(String uuid,CommonReqModel model,String[] topic,String pdf){
		int data=0;
		Map<String,Object> params=new HashMap<>();
		String query="";
		try {
//			System.out.println(uuid+jwt);
			query="""
					INSERT INTO emsadmin.paper_details
				(exam_id, pat_id, paper_id, paper_name, sub_id, chap_id, topic_id, total_ques, total_marks, paper_pdf, created_by, created_at,exam_date)
				VALUES(:exam, :exam_type, :paper_id, :exam_name, :subject, :chapter, :topic, :tot_qs, :tot_mrk, 'pdf', :uuid, now(),:exam_date);
				
					""";
//			System.out.println(model.getSubject());
//			System.out.println(model.getChapter());
//			System.out.println(model.getTopics());
			params.put("uuid", uuid);
			params.put("exam", model.getExam());
			params.put("paper_id", model.getPaperid());
			params.put("tot_qs", Integer.valueOf(model.getTotalQs()));
			params.put("tot_mrk", Integer.valueOf(model.getTotalMarks()));
			params.put("exam_name", model.getExamName());
			params.put("exam_date",java.sql.Date.valueOf(model.getExamDate()) );
			params.put("exam_type", model.getExamType());
			params.put("subject", model.getSubject());
			params.put("chapter", model.getChapter());
			params.put("topic", model.getTopics());
//			params.put("pdf", pdf);
			data=jdbctemplate.update(query, params);
		}catch(Exception ex) {
			ex.printStackTrace();
				throw ex;
			}
		return data;
	}
	
	public List<Map<String,Object>> getQuestionsRepo(String id,String type,int count){
		
		List<Map<String,Object>> data=null;
		String query="";
		Map<String,Object> params=new HashMap<>();
		try {
			
			
			System.out.println(id);
			
			query="""
					SELECT a.* FROM emsadmin.ques_ans_details AS a
					where (a.sub_id =:id or a.chap_id =:id or a.topic_id =:id )and a.ques_type =:type
					order by random() limit :count
					""";
			params.put("id", id);
			params.put("type", type);
			params.put("count", count);
//			System.out.println(params);
			data=jdbctemplate.queryForList(query, params);
		}
		catch(Exception ex) {
			throw ex;
		}
		return data;
	}
	
	
}
