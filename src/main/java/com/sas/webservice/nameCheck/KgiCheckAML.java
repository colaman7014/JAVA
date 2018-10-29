package com.sas.webservice.nameCheck;

import java.util.List;

import javax.jws.WebService;

import com.sas.webservice.nameCheck.bean.CheckAMLBean;
import com.sas.webservice.nameCheck.bean.OutputBean;

@WebService()
public interface KgiCheckAML {

	public List<OutputBean> checkAML(CheckAMLBean input);
	
}
