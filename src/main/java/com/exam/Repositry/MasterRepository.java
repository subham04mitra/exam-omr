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
				(exam_id, pat_id, paper_id, paper_name, sub_id, chap_id, topic_id, total_ques, total_marks,
				 paper_pdf, created_by, created_at,exam_date,paper_duration,each_mrk)
				VALUES(:exam, :exam_type, :paper_id, :exam_name, :subject, :chapter, :topic, :tot_qs, :tot_mrk,
				 :pdf, :uuid, now(),:exam_date,:duration,:each_mrk);
				
					""";
//			System.out.println(model.getSubject());
//			System.out.println(model.getChapter());
//			System.out.println(model.getTopics());
			params.put("uuid", uuid);
			params.put("exam", model.getExam());
			params.put("paper_id", model.getPaperid());
			params.put("tot_qs", Integer.valueOf(model.getTotalQs()));
			params.put("tot_mrk", Integer.valueOf(model.getTotalMarks()));
			params.put("duration", Integer.valueOf(model.getDuration()));
			params.put("exam_name", model.getExamName());
			params.put("exam_date",java.sql.Date.valueOf(model.getExamDate()) );
			params.put("exam_type", model.getExamType());
			params.put("subject", model.getSubject());
			params.put("chapter", model.getChapter());
			params.put("topic", model.getTopics());
			params.put("each_mrk", model.getEachMark());
			params.put("pdf", pdf);
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
			
			
//			System.out.println(id);
			
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
//			System.out.println(data);
		}
		catch(Exception ex) {
			throw ex;
		}
		return data;
	}
	
public List<Map<String,Object>> getUserDataRepo(String id){
		
		List<Map<String,Object>> data=null;
		String query="";
		Map<String,Object> params=new HashMap<>();
		try {
			
			
			query="""
					SELECT x.user_name,x.user_inst,x.user_branch,x."uuid",x.admin_id,x.owner_id
					  FROM masadmin.mas_user x where x.uuid =:id
					""";
			params.put("id", id);
			data=jdbctemplate.queryForList(query, params);
		}
		catch(Exception ex) {
			throw ex;
		}
		return data;
	}
	

public List<Map<String,Object>> getexamTypeDataRepo(String id){
	
	List<Map<String,Object>> data=null;
	String query="";
	Map<String,Object> params=new HashMap<>();
	try {
		
		
		query="""
				SELECT x.pattern_type  FROM masadmin.mas_exam_pattern x where x.pat_id =:id
				""";
		params.put("id", id);
		data=jdbctemplate.queryForList(query, params);
	}
	catch(Exception ex) {
		throw ex;
	}
	return data;
}


public List<Map<String,Object>> getExamNameRepo(String id){
	
	List<Map<String,Object>> data=null;
	String query="";
	Map<String,Object> params=new HashMap<>();
	try {
		
		
		query="""
				SELECT x.exam_name  FROM masadmin.mas_exam1 x where x.exam_id =:id 
				""";
		params.put("id", id);
		data=jdbctemplate.queryForList(query, params);
	}
	catch(Exception ex) {
		throw ex;
	}
	return data;
}


public List<Map<String,Object>> getQsPaperRepo(String id){
	
	List<Map<String,Object>> data=null;
	String query="";
	Map<String,Object> params=new HashMap<>();
	try {
		
		
		query="""
				SELECT x.paper_pdf FROM emsadmin.paper_details x where x.paper_id =:id 
				""";
		params.put("id", id);
//		System.out.println(params);
		data=jdbctemplate.queryForList(query, params);
//		System.out.println(data);
	}
	catch(Exception ex) {
		throw ex;
	}
	return data;
}


public List<Map<String,Object>> getQsPaperListRepo(String id,String type){
	
	List<Map<String,Object>> data=null;
	String query="";
	Map<String,Object> params=new HashMap<>();
	try {
		
		if("admin".equalsIgnoreCase(type)) {
			query="""
					
					SELECT 
				    x.paper_id,
				    x.paper_name,
				    me.exam_name,
				    mep.pattern_type ,
				    x.total_ques ,
				    x.total_marks ,
				    x.paper_duration ,
				   	mu.user_name ,
				   	mu.user_branch, 
				    Date(x.created_at) ,
				    array_agg(DISTINCT sd.subject_name) AS subjects,
				    array_agg(DISTINCT cd.chapter_name) AS chapters,
				    array_agg(DISTINCT td.topic_name) AS topics
				FROM emsadmin.paper_details x
				join masadmin.mas_user mu on x.created_by =mu."uuid" 
				JOIN masadmin.mas_exam1 me ON x.exam_id = me.exam_id
				JOIN masadmin.mas_exam_pattern mep ON x.pat_id = mep.pat_id
				LEFT JOIN LATERAL unnest(x.sub_id) AS s(sub_id) ON true
				LEFT JOIN emsadmin.subject_details sd ON sd.sub_id = s.sub_id
				LEFT JOIN LATERAL unnest(x.chap_id) AS c(chap_id) ON true
				LEFT JOIN emsadmin.chapter_details cd ON cd.chap_id = c.chap_id
				LEFT JOIN LATERAL unnest(x.topic_id) AS t(topic_id) ON true
				LEFT JOIN emsadmin.topic_details td ON td.topic_id = t.topic_id
				where mu.admin_id =:id or mu."uuid" =:id
				GROUP BY x.paper_id, x.paper_name, me.exam_name
				, mep.pattern_type,mu.user_name,mu.user_branch ;
	 
					""";
		}
		else if("owner".equalsIgnoreCase(type)) {
			query="""
					
					SELECT 
				    x.paper_id,
				    x.paper_name,
				    me.exam_name,
				    mep.pattern_type ,
				    x.total_ques ,
				    x.total_marks ,
				    x.paper_duration ,
				   	mu.user_name ,
				   	mu.user_branch, 
				    Date(x.created_at) ,
				    array_agg(DISTINCT sd.subject_name) AS subjects,
				    array_agg(DISTINCT cd.chapter_name) AS chapters,
				    array_agg(DISTINCT td.topic_name) AS topics
				FROM emsadmin.paper_details x
				join masadmin.mas_user mu on x.created_by =mu."uuid" 
				JOIN masadmin.mas_exam1 me ON x.exam_id = me.exam_id
				JOIN masadmin.mas_exam_pattern mep ON x.pat_id = mep.pat_id
				LEFT JOIN LATERAL unnest(x.sub_id) AS s(sub_id) ON true
				LEFT JOIN emsadmin.subject_details sd ON sd.sub_id = s.sub_id
				LEFT JOIN LATERAL unnest(x.chap_id) AS c(chap_id) ON true
				LEFT JOIN emsadmin.chapter_details cd ON cd.chap_id = c.chap_id
				LEFT JOIN LATERAL unnest(x.topic_id) AS t(topic_id) ON true
				LEFT JOIN emsadmin.topic_details td ON td.topic_id = t.topic_id
				where mu.owner_id =:id
				GROUP BY x.paper_id, x.paper_name, me.exam_name
				, mep.pattern_type,mu.user_name,mu.user_branch ;
	 
					""";
		}
		else if("teacher".equalsIgnoreCase(type)) {
			query="""
					
					SELECT 
				    x.paper_id,
				    x.paper_name,
				    me.exam_name,
				    mep.pattern_type ,
				    x.total_ques ,
				    x.total_marks ,
				    x.paper_duration ,
				   	mu.user_name ,
				   	mu.user_branch, 
				    Date(x.created_at) ,
				    array_agg(DISTINCT sd.subject_name) AS subjects,
				    array_agg(DISTINCT cd.chapter_name) AS chapters,
				    array_agg(DISTINCT td.topic_name) AS topics
				FROM emsadmin.paper_details x
				join masadmin.mas_user mu on x.created_by =mu."uuid" 
				JOIN masadmin.mas_exam1 me ON x.exam_id = me.exam_id
				JOIN masadmin.mas_exam_pattern mep ON x.pat_id = mep.pat_id
				LEFT JOIN LATERAL unnest(x.sub_id) AS s(sub_id) ON true
				LEFT JOIN emsadmin.subject_details sd ON sd.sub_id = s.sub_id
				LEFT JOIN LATERAL unnest(x.chap_id) AS c(chap_id) ON true
				LEFT JOIN emsadmin.chapter_details cd ON cd.chap_id = c.chap_id
				LEFT JOIN LATERAL unnest(x.topic_id) AS t(topic_id) ON true
				LEFT JOIN emsadmin.topic_details td ON td.topic_id = t.topic_id
				where mu."uuid" =:id
				GROUP BY x.paper_id, x.paper_name, me.exam_name
				, mep.pattern_type,mu.user_name,mu.user_branch ;
	 
					""";
			
		}
		
		
		params.put("id", id);
//		System.out.println(params);
		data=jdbctemplate.queryForList(query, params);
//		System.out.println(data);
	}
	catch(Exception ex) {
		throw ex;
	}
	return data;
}


public List<Map<String,Object>> getavlQsRepo(String id){
	
	List<Map<String,Object>> data=null;
	String query="";
	Map<String,Object> params=new HashMap<>();
	try {
		
		
		query="""
				SELECT x.ques_type ,count(1) FROM emsadmin.ques_ans_details x 
				where (x.sub_id =:id or x.chap_id =:id or x.topic_id =:id) group by x.ques_type """;
		params.put("id", id);
//		System.out.println(params);
		data=jdbctemplate.queryForList(query, params);
//		System.out.println(data);
	}
	catch(Exception ex) {
		throw ex;
	}
	return data;
}


public int saveAnsSheetRepo(String uuid,String[] ans,String paper_id){
	int data=0;
	Map<String,Object> params=new HashMap<>();
	String query="";
	try {
//		System.out.println(uuid+jwt);
		query="""
				INSERT INTO emsadmin.answer_details
				(paper_id, ques_id, answer, positive_marks, negetive_marks, created_by, created_at)
				VALUES(:paper_id, '', :ans, 0, 0, :uuid, now());
				""";
//		System.out.println(model.getSubject());
//		System.out.println(model.getChapter());
//		System.out.println(model.getTopics());
		params.put("uuid", uuid);
		params.put("paper_id", paper_id);
		params.put("ans", ans);
		
		data=jdbctemplate.update(query, params);
	}catch(Exception ex) {
		ex.printStackTrace();
			throw ex;
		}
	return data;
}
public int saveUserRepo(String owner,CommonReqModel model,String admin,String uuid){
	int data=0;
	
	Map<String,Object> params=new HashMap<>();
	String query="";
	try {
//		System.out.println(uuid+jwt);
		query="""
				INSERT INTO masadmin.mas_user
				("uuid", user_pwd, user_name, user_role, user_inst, user_branch, active_flag,
				 entry_ts, entry_by, user_email, user_mobile, owner_id, admin_id)
				VALUES(:id, :pwd, :name, :role, :inst, :branch, 'Y',
				 now(), :uuid, :email, :mobile, :owner, :admin);
			
				""";
//		System.out.println(model.getSubject());
//		System.out.println(model.getChapter());
//		System.out.println(model.getTopics());
		params.put("id",model.getId().toString() );
		params.put("pwd", model.getPwd());
		params.put("name", model.getName());
		params.put("role", model.getRole());
		params.put("inst", model.getInst());
		params.put("branch", model.getBranch());
		params.put("uuid", uuid);
		params.put("email",model.getEmail() );
		params.put("mobile",model.getMobile());
		params.put("owner",owner);
		params.put("admin",admin);
		
		data=jdbctemplate.update(query, params);
	}catch(Exception ex) {
		ex.printStackTrace();
			throw ex;
		}
	return data;
}


public List<Map<String,Object>> getOwnerDataRepo(String id){
	
	List<Map<String,Object>> data=null;
	String query="";
	Map<String,Object> params=new HashMap<>();
	try {
		
		
		query="""
				select mu.user_name,mu."uuid",mu.user_branch from masadmin.mas_user mu 
				where mu.admin_id =:id and mu.user_role ='OWNER'
				""";
		params.put("id", id);
		data=jdbctemplate.queryForList(query, params);
	}
	catch(Exception ex) {
		throw ex;
	}
	return data;
}

public List<Map<String,Object>> checkUserIdRepo(String id){
	
	List<Map<String,Object>> data=null;
	String query="";
	Map<String,Object> params=new HashMap<>();
	try {
		
		
		query="""
				select * from masadmin.mas_user mu where mu."uuid" =:id
				""";
		params.put("id", id);
//		System.out.println(params);
		data=jdbctemplate.queryForList(query, params);
	}
	catch(Exception ex) {
		throw ex;
	}
	return data;
}


public List<Map<String,Object>> getStudentDataRepo(String id){
	
	List<Map<String,Object>> data=null;
	String query="";
	Map<String,Object> params=new HashMap<>();
	try {
		
		query="""
				SELECT x.student_name,x.student_id,pd.total_ques ,pd.total_marks ,
				x.tot_attempt ,x.tot_correct ,x.tot_wrong ,pd.each_mrk 
			FROM emsadmin.student_data x,emsadmin.paper_details pd 
			WHERE x.paper_id =:id and x.paper_id =pd.paper_id
				""";
		params.put("id", id);
		data=jdbctemplate.queryForList(query, params);
//		System.out.println(data);
	}
	catch(Exception ex) {
		throw ex;
	}
	return data;
}

public List<Map<String,Object>> getQsPaperListbycndRepo(String id,String type,CommonReqModel model){
	
	List<Map<String,Object>> data=null;
	String query="";
	Map<String,Object> params=new HashMap<>();
	try {
		
		if("admin".equalsIgnoreCase(type)) {
			query="""
					select x.paper_name,x.exam_date,mu.user_name,mu.user_branch,x.paper_id
					from emsadmin.paper_details x,masadmin.mas_user mu 
					where x.exam_id =:exam and x.pat_id =:pat 
					and mu."uuid" =x.created_by and (mu."uuid" =:id or mu.admin_id =:id)
					""";
		}
		else if("owner".equalsIgnoreCase(type)) {
			query="""
					
					select x.paper_name,x.exam_date,mu.user_name,mu.user_branch,x.paper_id
					 from emsadmin.paper_details x,masadmin.mas_user mu 
					where x.exam_id =:exam and x.pat_id =:pat 
					and mu."uuid" =x.created_by and (mu."uuid" =:id' or mu.owner_id =:id')
						 
					""";
		}
		else if("teacher".equalsIgnoreCase(type)) {
			query="""
					select x.paper_name,x.exam_date,mu.,mu.user_branch,x.paper_id
					 from emsadmin.paper_details x,masadmin.mas_user mu
					where x.exam_id =:exam and pat_id =:pat and created_by =:id and mu."uuid" =x.created_by
					""";
			
		}
		
		
		params.put("id", id);
		params.put("exam", model.getExam());
		params.put("pat", model.getExamType());
//		System.out.println(params);
		data=jdbctemplate.queryForList(query, params);
//		System.out.println(data);
	}
	catch(Exception ex) {
		throw ex;
	}
	return data;
}


}
