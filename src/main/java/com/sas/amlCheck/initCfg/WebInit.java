package com.sas.amlCheck.initCfg;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInit implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(AppCtx.class);
		ContextLoaderListener ctxListener = new ContextLoaderListener(appContext);
		servletContext.addListener(ctxListener);
		
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(WebCtx.class);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
		Dynamic addServlet = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
		addServlet.setLoadOnStartup(1);
		addServlet.addMapping("/");
		
		CXFServlet cxfSevlet = new CXFServlet();
		Dynamic cxfServletDynamic = servletContext.addServlet("CXFService", cxfSevlet);
		cxfServletDynamic.addMapping("/ws/*");
	}

}
