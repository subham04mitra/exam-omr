package com.exam.Repositry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DashboradRepo {

	@Autowired
	NamedParameterJdbcTemplate jdbctemplate;
	

	public List<Map<String,Object>> dashboradDynamicRepo(String uuid,String type){
		List<Map<String,Object>> data=null;
		String query="";
		Map<String,Object> params=new HashMap<>();
		try {
		
			if("Admin".equalsIgnoreCase(type)) {
				query="""
						SELECT sum(x.teacher) as teacher,sum(x.paper_generated) as papers,sum(x.omr_scan) as scans,
						sum(x.avg_marks)/count(1) as avg_marks, 
						(select count(distinct user_branch) from masadmin.mas_user mu 
						where admin_id =:uuid or "uuid" =:uuid) as tot_branch
						FROM masadmin.dashboard_data x,masadmin.mas_user mu
						where x.inst=mu.user_inst and mu."uuid"=:uuid
						""";
			}
			else if("Owner".equalsIgnoreCase(type)) {
				query="""
					SELECT x.teacher ,x.paper_generated as papers,x.omr_scan as scans,
					x.avg_marks , 1 as tot_branch
					FROM masadmin.dashboard_data x,masadmin.mas_user mu
					where x.inst=mu.user_inst and mu."uuid"=:uuid and x.branch =mu.user_branch
					""";
			}
			else {
				query="""
						SELECT x.tot_paper as papers,x.tot_scan as scans FROM masadmin.teacher_history x 
						where x."uuid" =:uuid
						""";
			}
			
			params.put("uuid", uuid);
			
			data=jdbctemplate.queryForList(query, params);
		}catch(Exception ex) {
			throw ex;
		}
		return data;
	}
	
	
	
	public List<Map<String,Object>> getTotQsRepo(){
		List<Map<String,Object>> data=null;
		String query="";
		Map<String,Object> params=new HashMap<>();
		try {
			
			query="select count(1) from emsadmin.ques_ans_details qad where qad.active_yn ='Y'";
			
			
			data=jdbctemplate.queryForList(query, params);
		}catch(Exception ex) {
			throw ex;
		}
		return data;
	}
	



}

