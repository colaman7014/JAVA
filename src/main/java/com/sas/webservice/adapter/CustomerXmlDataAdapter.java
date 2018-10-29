package com.sas.webservice.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.sas.constraint.SwiftMtConst;

public class CustomerXmlDataAdapter extends XmlAdapter<Object, Object>{
	
	@Override
	public Object unmarshal(Object v) throws Exception {
		// TODO Auto-generated method stub
		return v;
	}

	@Override
	public Object marshal(Object v) throws Exception {
		// TODO Auto-generated method stub
		if(v == null || "".equals(v)){
			v = SwiftMtConst.ERROR_CODE_EMPTY_MESSAGE;
		}
		return v;
	}
	
}
