package com.sas.webservice.swiftCheck;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.servlet.ServletContext;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.sas.aml.retry.event.queue.bean.XRetryEventQueue;
import com.sas.aml.retry.event.queue.service.QueueConstraint;
import com.sas.aml.retry.event.queue.service.XRetryEventQueueService;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.dao.sc.ISwiftLogTimeDao;
import com.sas.db.wlf.orm.sc.SwiftLogTime;
import com.sas.util.AmlConfiguration;
import com.sas.webservice.createCase.AmlCreateCase;
import com.sas.webservice.swiftCheck.bean.SwiftCheckInputBean;
import com.sas.webservice.swiftCheck.bean.SwiftCheckOutputBean;
import com.sas.wsdl.swiftCheckReport.AmlSwiftCheckReport;
import com.sas.wsdl.swiftCheckReport.AmlSwiftCheckReportImplService;
import com.sas.wsdl.swiftCheckReport.SwiftCheckReportInputBean;
import com.sas.wsdl.swiftCheckReport.SwiftCheckReportOutputBean;

/**
 * AML SWIFT Async CHECK
 * 
 * @author Eric Su
 *
 */

@Async
@Component
@Scope("prototype")
public class AmlSwiftAsyncCheck {
	private static final Logger logger = LoggerFactory.getLogger(AmlSwiftAsyncCheck.class);
	private long Count = 0;
	
	@Autowired
	ServletContext context; 
	
	@Autowired @Lazy
	AmlSwiftCheck amlSwiftCheck;
	
	@Autowired @Lazy
	AmlCreateCase amlCreateCase;
	
	@Autowired @Lazy
	XRetryEventQueueService xRetryEventQueueService;
	
	@Autowired @Lazy
	ISwiftLogTimeDao iSwiftLogTimeDao;
	
	private static BeanCopier swiftCheckInputBeanCopier;
	
	private static List<SwiftCheckInputBean> buffer = Collections.synchronizedList(new ArrayList<SwiftCheckInputBean>()); // 同步鎖實例

	static {
		swiftCheckInputBeanCopier = BeanCopier.create(SwiftCheckInputBean.class, SwiftCheckInputBean.class, false);
	}

	public AmlSwiftAsyncCheck() {
	}

	public static void putSwiftCheckInputBean(SwiftCheckInputBean source) {
		SwiftCheckInputBean copied = new SwiftCheckInputBean();
		swiftCheckInputBeanCopier.copy(source, copied, null);
		buffer.add(copied);
	}

	private SwiftCheckOutputBean doWebCallSwiftCheck(SwiftCheckInputBean input, long id) throws Exception{
		SwiftCheckOutputBean output = new SwiftCheckOutputBean();
			output = amlSwiftCheck.SwiftAsyncCheck(input, id);
		return output;
	}

	
	@Async (value = "threadPoolTaskExecutor")
    public Future<String> doAsyncSwiftCheck(SwiftCheckInputBean input, long id){  
		Count++;
		logger.info("============ 啟動新線程::::: "+ Thread.currentThread().getName() + "===============\n" );
		logger.info("(((((((((((((((((((((( 第幾"+Count+"次))))))))))))))))))))))，時間::::" + System.currentTimeMillis());
		try {
			Date currentDateTime = new Date();
			Timestamp currentTimestamp = new Timestamp(currentDateTime.getTime());
			SwiftLogTime swiftLogTime = iSwiftLogTimeDao.findOne(input.getUniqueKey());
			swiftLogTime.setTime3(currentTimestamp);
			iSwiftLogTimeDao.save(swiftLogTime);
			SwiftCheckOutputBean output = doWebCallSwiftCheck(input, id);
			
			if (output == null) {
				logger.info("---------------------------- 失敗重做 --------------------------");
				//如果失敗要重做的話
				return new AsyncResult<>("Fail!");
			}
			currentDateTime = new Date();
			currentTimestamp = new Timestamp(currentDateTime.getTime());
			swiftLogTime.setTime4(currentTimestamp);
			iSwiftLogTimeDao.save(swiftLogTime);
			
			SwiftCheckReportInputBean swiftCheckReportInputBean = new SwiftCheckReportInputBean();
			swiftCheckReportInputBean.setNcReferenceId(output.getNcReferenceId());
			swiftCheckReportInputBean.setUniqueKey(output.getUniqueKey());
			swiftCheckReportInputBean.setBranchNumber(input.getBranchNumber());
			swiftCheckReportInputBean.setUnit(input.getBusinessUnitId());
			swiftCheckReportInputBean.setNcResult(SwiftMtConst.NC_RESULT_PENDING.equalsIgnoreCase(output.getNcResult()) ? SwiftMtConst.NC_RESULT_HIT : output.getNcResult() );
			swiftCheckReportInputBean.setHitListSession(output.getRouteRule());
			// TODO
			swiftCheckReportInputBean.setNcCaseId(null);
			swiftCheckReportInputBean.setNcCaseStatus(null);

			logger.debug("\n ---------------------- Request ----------------------- \n "
					+ ToStringBuilder.reflectionToString(swiftCheckReportInputBean, ToStringStyle.SHORT_PREFIX_STYLE)
					+ "\n ---------------------- swiftCheckReportInputBean ----------------------- \n "
			);
			
			logger.info("#################### BOT Swift AC To SWALLOW #####################\n");
			currentDateTime = new Date();
			currentTimestamp = new Timestamp(currentDateTime.getTime());
			swiftLogTime.setTime5(currentTimestamp);
			iSwiftLogTimeDao.save(swiftLogTime);
			boolean isSuccess = amlCreateCase.AcSwallowSwiftStatus(swiftCheckReportInputBean, 0);
			currentDateTime = new Date();
			currentTimestamp = new Timestamp(currentDateTime.getTime());
			swiftLogTime.setTime6(currentTimestamp);
			iSwiftLogTimeDao.save(swiftLogTime);
			logger.info("============ SwiftCheck AC To Swallow isSuccess :::: "+ isSuccess +" ===============\n" );
		} catch (Exception ex) {
			logger.error(String.format("doSwiftAsyncCheck fail, exception : ", ex.getMessage()), ex);
			XRetryEventQueue xRetryEventQueue = xRetryEventQueueService.saveEntityToXRetryEventQueue(SwiftMtConst.SOURCE_TYPE_RT_SC, SwiftCheckInputBean.class.getName(),
					input, HttpMethod.POST, 
					AmlConfiguration.getString(QueueConstraint.COM_SAS_BASE_URL) +  context.getContextPath() + "/rest/retrySwiftCheck",
					3, "retrySwiftCheck", id, QueueConstraint.QUEUE_EVENT_STATUS_WAITING, false, "");
			if(xRetryEventQueue == null) {
				logger.error("Retry id insert fail");
			}
			return new AsyncResult<>("Fail");
		}
          
        return new AsyncResult<>("Complete");  
    }  
	/**
	 * 實作非同步SWIFT電文判斷
	 * 
	 * @param input
	 */
	@Async
	public void doSwiftAsyncCheck(SwiftCheckInputBean input) {
		try {
			SwiftCheckOutputBean output = doWebCallSwiftCheck(input, 0);
			if (output == null) {
				logger.info("---------------------------- 失敗重做 --------------------------");
				//如果失敗要重做的話
				return;
			}

			// JAX-WS
			URL swiftChkRptUrl = new URL(AmlConfiguration.getString(SwiftMtConst.COM_SAS_SWIFTCHECKREPORT_URL));
			AmlSwiftCheckReportImplService amlSwiftCheckReportImplService = new AmlSwiftCheckReportImplService(swiftChkRptUrl);
			AmlSwiftCheckReport port = amlSwiftCheckReportImplService.getAmlSwiftCheckReportImplPort();
			
			HTTPConduit httpConduit = (HTTPConduit) ClientProxy.getClient(port).getConduit();

			TLSClientParameters tlsCP = new TLSClientParameters();
			// other TLS/SSL configuration like setting up TrustManagers
			tlsCP.setDisableCNCheck(true);
			httpConduit.setTlsClientParameters(tlsCP);
			
			SwiftCheckReportInputBean swiftCheckReportInputBean = new SwiftCheckReportInputBean();
			swiftCheckReportInputBean.setNcReferenceId(output.getNcReferenceId());
			swiftCheckReportInputBean.setUniqueKey(output.getUniqueKey());
			swiftCheckReportInputBean.setBranchNumber(input.getBranchNumber());
			swiftCheckReportInputBean.setUnit(input.getBusinessUnitId());
			swiftCheckReportInputBean.setNcResult(output.getNcResult());
			swiftCheckReportInputBean.setHitListSession(output.getRouteRule());
			// TODO
			swiftCheckReportInputBean.setNcCaseId(null);
			swiftCheckReportInputBean.setNcCaseStatus(null);
			
			if (port != null) {
				SwiftCheckReportOutputBean swiftCheckReportOutputBean = port.swiftCheckReport(swiftCheckReportInputBean);				
			} else {
				logger.error("Load AmlSwiftCheckReportImplService fail !");
			}
		} catch (Exception ex) {
			logger.error(String.format("doSwiftAsyncCheck fail, exception : ", ex.getMessage()), ex);
		}
	}
}
