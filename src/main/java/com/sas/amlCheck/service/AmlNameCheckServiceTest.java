package com.sas.amlCheck.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.sas.webservice.nameCheck.bean.NameCheckInputRestBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputRestBean;

public class AmlNameCheckServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(AmlNameCheckServiceTest.class);
	
	public List<NameCheckOutputRestBean> getResult(){
		List<NameCheckOutputRestBean> resultList = new ArrayList<NameCheckOutputRestBean>();
		
		NameCheckInputRestBean request = new NameCheckInputRestBean();
		request.setCh_name("陳水扁");
		request.setType_desc("ALL");
		try{
			NameCheckOutputRestBean response = onlineNameCheck(request);
		}catch(Exception e){
			logger.error("getResult fail exception : }", e);
		}
		
		return resultList;
	}
	
	private NameCheckOutputRestBean onlineNameCheck(NameCheckInputRestBean request){
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://127.0.0.1:8080/AmlCheck/rest/onlineNameCheck";
		return restTemplate.postForObject(url, request, NameCheckOutputRestBean.class);
	}
}
