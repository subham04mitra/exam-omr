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
	private String user_pwd;
	private String token;
	private String subject;
	private String chapter;
	private String examName;
	private String duration;
	private String examDate;
	private String totalMarks;
	private String totalQs;
	private String exam;
	private String examType;
	private Object topics;
	
	
	
}
