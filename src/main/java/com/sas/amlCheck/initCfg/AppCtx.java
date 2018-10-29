package com.sas.amlCheck.initCfg;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.sas.webservice.batch.screening.BatchNameCheckingChangeScan;
import com.sas.webservice.batch.screening.BatchNameCheckingFullPartyFullScan;
import com.sas.webservice.batch.screening.BatchNameCheckingResult;
import com.sas.webservice.batch.screening.BatchSwiftScreening;
import com.sas.webservice.batch.screening.BatchTransactionScreening;
import com.sas.webservice.billLadingsInvoiceStatus.AmlBLInvStatus;
import com.sas.webservice.nameCheck.AmlBatchNameCheck;
import com.sas.webservice.nameCheck.AmlFtpExRemittanceNameCheck;
import com.sas.webservice.nameCheck.AmlFtpNameCheck;
import com.sas.webservice.nameCheck.AmlFtpRemittanceNameCheck;
import com.sas.webservice.nameCheck.AmlNameCheck;
import com.sas.webservice.nameCheckStatus.AmlNameCheckStatus;
import com.sas.webservice.swiftCheck.AmlSwiftAsyncCheck;
import com.sas.webservice.swiftCheck.AmlSwiftCheck;
import com.sas.webservice.swiftCheckStatus.AmlSwiftCheckStatus;

/**
 * CXF 自動生成 WebService
 * @author SAS
 *
 */
@EnableScheduling
@EnableAsync 
@Configuration
@Import(value = {AmlJpaCfg.class })
@ComponentScan(basePackages = { "com.sas.aml.retry.event.queue.service","com.sas.cfg", "com.sas.wlsearch", "com.sas.tradeFinance.service", "com.sas.multipleNC.service", "com.sas.webservice",
		"com.sas.createcase.business", "com.sas.amlCheck.service", "com.sas.db.wlf.dao"})
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppCtx {
	@Autowired
	AmlNameCheck amlNameCheckImpl;
	@Autowired
	AmlBatchNameCheck amlBatchNameCheckImpl;
	@Autowired
	AmlNameCheckStatus amlNameCheckStatusImpl;
	@Autowired
	AmlSwiftCheck amlSwiftCheckImpl;
	@Autowired
	AmlSwiftCheckStatus amlSwiftCheckStatusImpl;
	@Autowired
	AmlBLInvStatus amlBLInvStatusImpl;
	@Autowired
	BatchNameCheckingChangeScan batchNameCheckingChangeScanImpl;
	@Autowired
	BatchTransactionScreening batchTransactionScreeningImpl;
	@Autowired
	BatchSwiftScreening batchSwiftScreeningImpl;
	@Autowired  
	BatchNameCheckingFullPartyFullScan batchNameCheckingFullPartyFullScanImpl;
	@Autowired
	BatchNameCheckingResult batchNameCheckingResultImpl;
	@Autowired
	AmlFtpNameCheck amlFtpNameCheckImpl;
	@Autowired
	AmlFtpRemittanceNameCheck amlFtpRemittanceNameCheckImpl;
	@Autowired
	AmlFtpExRemittanceNameCheck amlFtpExRemittanceNameCheckImpl;

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
	 * ReamTime AML Name Check WebService
	 * 
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint amlBatchNameCheck(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, amlBatchNameCheckImpl);
		end.publish("/amlBatchNameCheck");
		return end;
	}

	/**
	 * RealTime AML SWIFT Check WebService
	 * 
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint amlSwiftCheck(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, amlSwiftCheckImpl);
		end.publish("/amlSwiftCheck");
		return end;
	}

	/**
	 * RealTeim AML Name Check Status WebService
	 * 
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint amlNameCheckStatus(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, amlNameCheckStatusImpl);
		end.publish("/amlNameCheckStatus");
		return end;
	}

	/**
	 * Real Time AML SWIFT Check Status WebService
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint amlSwiftCheckStatus(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, amlSwiftCheckStatusImpl);
		end.publish("/amlSwiftCheckStatus");
		return end;
	}

	@Bean
	public Endpoint amlBLInvStatus(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, amlBLInvStatusImpl);
		end.publish("/amlBLInvStatus");
		return end;
	}
	
	/**
	 * Batch Transaction Screening WebService
	 * 
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint batchNameCheckingChangeScan(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, batchNameCheckingChangeScanImpl);
		end.publish("/batchNameCheckingChangeScan");
		return end;
	}
	
	/**
	 * Batch Transaction Screening WebService
	 * 
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint batchTransactionScreening(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, batchTransactionScreeningImpl);
		end.publish("/batchTransactionScreening");
		return end;
	}

	/**
	 * Batch Swift Screening WebService
	 * 
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint batchSwiftScreening(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, batchSwiftScreeningImpl);
		end.publish("/batchSwiftScreening");
		return end;
	}
	
	/**
	 * Batch Name Checking Full Party Full Scan WebService
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint batchNameCheckingFullPartyFullScan(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, batchNameCheckingFullPartyFullScanImpl);
		end.publish("/batchNameCheckingFullPartyFullScan");
		return end;
	}

	/**
	 * Batch Name NameChecking Result WebService
	 * 
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint batchBatchNameCheckingResult(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, batchNameCheckingResultImpl);
		end.publish("/batchNameCheckingResult");
		return end;
	}

	/**
	 * AML Ftp Name Check WebService
	 * 
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint amlFtpNameCheck(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, amlFtpNameCheckImpl);
		end.publish("/amlFtpNameCheck");
		return end;
	}

	/**
	 * AML Remittance Ftp Name Check WebService
	 * 
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint amlFtpRemittanceNameCheck(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, amlFtpRemittanceNameCheckImpl);
		end.publish("/amlFtpRemittanceNameCheck");
		return end;
	}
	
	
	/**
	 * AML Ex Remittance Ftp Name Check WebService
	 * 
	 * @param context
	 * @return
	 */
	@Bean
	public Endpoint amlFtpExRemittanceNameCheck(ApplicationContext context) {
		Bus bus = (Bus) context.getBean(Bus.DEFAULT_BUS_ID);
		Endpoint end = new EndpointImpl(bus, amlFtpExRemittanceNameCheckImpl);
		end.publish("/amlFtpExRemittanceNameCheck");
		return end;
	}
	
	
	// 20180202
	/**
	 * Thread Pool Task Executor
	 * @return ThreadPoolTaskExecutor
	 */
	@Bean(name = "threadPoolTaskExecutor")
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(50);
		executor.setQueueCapacity(10000);
		executor.setKeepAliveSeconds(30000);
		executor.setThreadNamePrefix("AML-Executor");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());  
        executor.initialize();    
		return executor;
	}

	/**
	 * ASynchronized AML SWIFT Check Status
	 * 
	 * @return AmlSwiftAsyncCheck
	 */
	@Bean
	public AmlSwiftAsyncCheck swiftAsyncCheck() {
		AmlSwiftAsyncCheck swiftAsyncCheck = new AmlSwiftAsyncCheck();
		return swiftAsyncCheck;
	}

}
