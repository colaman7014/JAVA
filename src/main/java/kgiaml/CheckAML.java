package kgiaml;

import java.util.List;

import javax.jws.WebService;

import com.sas.webservice.nameCheck.bean.CheckAMLBean;
import com.sas.webservice.nameCheck.bean.OutputBean;

@WebService
public interface CheckAML {
	
	public List<OutputBean> checkAML(CheckAMLBean caBean);

}
