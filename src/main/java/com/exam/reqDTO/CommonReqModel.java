package com.exam.reqDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonReqModel {

	
	private String uuid;
	private String paperid;
	private String paper_id;
	private String user_pwd;
	private String token;
	private Object subject;
	private Object chapter;
	private String examName;
	private String duration;
	private String examDate;
	private int totalMarks;
	private int totalQs;
	private String exam;
	private String examType;
	private Object topics;
	
	
	
}
