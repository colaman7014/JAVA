package kgiaml;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sas.webservice.nameCheck.KgiCheckAML;
import com.sas.webservice.nameCheck.bean.CheckAMLBean;
import com.sas.webservice.nameCheck.bean.OutputBean;

@Component
@WebService(targetNamespace = "http://kgiaml/", portName = "CheckAMLPort",
	serviceName = "CheckAMLService", wsdlLocation="wsdl/checkaml.wsdl")
public class CheckAMLImpl implements CheckAML{
	
	@Autowired
	KgiCheckAML kgiCheckAMLImpl;
	
	static Logger log = Logger .getLogger(CheckAMLImpl.class);
	@WebMethod(operationName = "checkAML", action = "urn:CheckAML")
	public List<OutputBean> checkAML(@WebParam(name = "arg0") CheckAMLBean caBean){
		return kgiCheckAMLImpl.checkAML(caBean);
	}

}
