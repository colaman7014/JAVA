package com.sas.amlCheck.initCfg;

import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.sas.webservice.nameCheck.AmlNameCheck;
import kgiaml.CheckAML;


/**
 * CXF 自動生成 WebService
 * 
 * @author SAS
 *
 */
@ServletComponentScan
@EnableScheduling
@EnableAsync
@Configuration
@Import(value = { AmlJpaCfg.class })
@ComponentScan(basePackages = { "com.sas.wlsearch", "com.sas.webservice", "com.sas.amlCheck.service",
		"com.sas.db.wlf.dao", "kgiaml" })
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppCtx {
	@Autowired
	AmlNameCheck amlNameCheckImpl;
	@Autowired
	CheckAML checkAMLImpl;
	/**
	 * ReamTime AML Name Check WebService
	 * 
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint amlNameCheck(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, amlNameCheckImpl);
		end.publish("/amlNameCheck");
		return end;
	}

	/**
	 * RealTeim AML Name Check WebService
	 * KGI
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint checkAML(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, checkAMLImpl);
		end.publish("/CheckAMLPort");
		return end;
	}
}
