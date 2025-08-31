package com.exam.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.Exception.GlobalExceptionHandler;
import com.exam.Repositry.DashboradRepo;
import com.exam.Response.ApiResponses;
import com.exam.Response.ResponseBean;
import com.exam.Security.TokenService;
import com.exam.resDTO.DashResModel;

@Service
public class DashboardService {

	@Autowired
	DashboradRepo dashboardrepo;
	@Autowired
	TokenService tokenservice;
	public ResponseEntity<ApiResponses> dashboardDynamicService(ResponseBean response, String authToken){
		List<Map<String,Object>> data=null;
		DashResModel dashres=new DashResModel();
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
				data=dashboardrepo.dashboradDynamicRepo(uuid, role);
				if(!data.isEmpty() && data!=null) {
					if("Teacher".equalsIgnoreCase(role)) {
						dashres.setTot_paper(data.get(0).get("papers"));
						dashres.setTot_scan(data.get(0).get("scans"));
					}
					else {
						dashres.setTot_paper(data.get(0).get("papers"));
						dashres.setTot_scan(data.get(0).get("scans"));
						dashres.setTot_teacher(data.get(0).get("teacher"));
						dashres.setAvg_marks(data.get(0).get("avg_marks"));
					}
					return response.AppResponse("Success", null, dashres);
				}
				else {
					return response.AppResponse("Notfound", null, null);
				}
		}catch(Exception ex) {
			throw ex;
		}
	}
}
