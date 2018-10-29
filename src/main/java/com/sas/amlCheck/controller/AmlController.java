package com.sas.amlCheck.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sas.amlCheck.service.AmlNameCheckService;
import com.sas.amlCheck.service.OnlineNameQueryOptionService;
import com.sas.db.aml.orm.ecm.FullRefTableTran;
import com.sas.webservice.nameCheck.bean.NameCheckInputRestBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputRestBean;

@RestController
@RequestMapping("/rest")
public class AmlController{
	private static final Logger logger = LoggerFactory.getLogger(AmlController.class);
	@Autowired
	AmlNameCheckService amlNameCheckService;
	@Autowired
	OnlineNameQueryOptionService onlineNameQueryOptionService;
	
	//for check rest api live
	@RequestMapping(value="/test/{str}", method=RequestMethod.GET)
	public void test(@PathVariable String str) {
		logger.debug("test str : {}", str);
	}

	
	@RequestMapping(value="/onlineNameCheck", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public NameCheckOutputRestBean onlineNameCheck(@RequestBody NameCheckInputRestBean nameCheckInputRestBean, HttpServletRequest req) {
		String locale = com.sas.util.StringUtils.getLocale(req.getLocale());
		logger.info(String.format("Input : %s ", ToStringBuilder.reflectionToString(nameCheckInputRestBean)));
		logger.info(String.format("Locale : %s ", locale));
		return amlNameCheckService.onlineNameCheck(nameCheckInputRestBean, locale);
	}
	
	
	@RequestMapping(value="/onlineNameQueryOptionService", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<FullRefTableTran> onlineNameQuerytypeDesc(HttpServletRequest req) {
		String locale = com.sas.util.StringUtils.getLocale(req.getLocale());
		logger.info(String.format("Locale : %s ", locale));
		return onlineNameQueryOptionService.onlineNameQuerytypeDesc(locale);
	}
	
}
