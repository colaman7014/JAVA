package com.sas.webservice.batch.screening;


import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.ICaseVersionDao;
import com.sas.db.aml.orm.ecm.CaseVersion;
import com.sas.db.wlf.dao.nc.INameCheckRecordDetailDao;
import com.sas.db.wlf.dao.nc.INameCheckRecordMainDao;
import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;
import com.sas.db.wlf.orm.nc.NameCheckRecordMain;
import com.sas.util.AmlConfiguration;
import com.sas.util.FTPUtil;
import com.sas.webservice.batch.screening.bean.BatchNameCheckingOutputFtpBean;
import com.sas.webservice.batch.screening.bean.BatchNameCheckingResultInputBean;
import com.sas.webservice.batch.screening.bean.BatchNameCheckingResultOutputBean;


@Component
@WebService(endpointInterface = "com.sas.webservice.batch.screening.BatchNameCheckingResult")
public class BatchNameCheckingResultImpl implements BatchNameCheckingResult {
	private static final Logger logger = LoggerFactory.getLogger(BatchNameCheckingResultImpl.class);

	@Autowired
	INameCheckRecordMainDao iNameCheckRecordMainDao;
	@Autowired
	INameCheckRecordDetailDao iNameCheckRecordDetailDao;
	@Autowired
	ICaseVersionDao iCaseVersionDao;

	@Override
	@Transactional
	public BatchNameCheckingResultOutputBean batchNameCheckingResultImpl(BatchNameCheckingResultInputBean input) {
		// TODO Auto-generated method stub

		BatchNameCheckingResultOutputBean batchNameCheckingResultOutputBean = new BatchNameCheckingResultOutputBean();
		batchNameCheckingResultOutputBean.setFinish(false);
		batchNameCheckingResultOutputBean.setServerName(System.getenv("COMPUTERNAME"));

		String interfaceName = SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECK;
		String ScreenProcess = SwiftMtConst.SCREEN_PROCESS_BATCH_NAME_CHECKING;
		String ncResultHit = SwiftMtConst.NC_RESULT_PENDING;
		String sourceTypeBtNc = SwiftMtConst.SOURCE_TYPE_BT_NC;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdfDTOutput = new SimpleDateFormat("yyyyMMddHHmmss");
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		List<BatchNameCheckingOutputFtpBean> batchNameCheckingOutputFtpList = new ArrayList<BatchNameCheckingOutputFtpBean>();
		
		//1.處理當日Hit(未結案)
		List<NameCheckRecordMain> nameCheckRecordMainList = (List<NameCheckRecordMain>) iNameCheckRecordMainDao
				.findByInterfaceNameAndScreenProcessAndTransactionDateGreaterThanEqualAndNcResult(interfaceName,
						ScreenProcess, Timestamp.valueOf(sdf.format(now) + " 00:00:00"), ncResultHit);
		logger.info("111111111111111111111111111111111 size()="  + nameCheckRecordMainList.size());
		for(NameCheckRecordMain mainBe : nameCheckRecordMainList){
			BatchNameCheckingOutputFtpBean batchNameCheckingOutputFtpBean  = new BatchNameCheckingOutputFtpBean();
			
			//處理存於Main檔中的資訊
			batchNameCheckingOutputFtpBean.setParty_number(mainBe.getPartyNumber());
			batchNameCheckingOutputFtpBean.setName_check_status(mainBe.getNcResult());
			batchNameCheckingOutputFtpBean.setName_check_date(mainBe.getTransactionDate());
			batchNameCheckingOutputFtpBean.setHit_route(mainBe.getRouteRule());
			
			//處理存於Detail檔中的資訊
			List<NameCheckRecordDetail> nameCheckRecordDetailList =  iNameCheckRecordDetailDao.findByIdUniqueKeyAndIdNcReferenceId(mainBe.getId().getUniqueKey(), mainBe.getId().getNcReferenceId());
			for(NameCheckRecordDetail detailBe : nameCheckRecordDetailList){
				String [] checkSeqSpilit = detailBe.getId().getCheckSeq().split("_!_");
				if(checkSeqSpilit.length > 1){  //代表有關係				
					batchNameCheckingOutputFtpBean.setRelated_party_number(batchNameCheckingOutputFtpBean.getParty_number());
					batchNameCheckingOutputFtpBean.setParty_number(checkSeqSpilit[1]);			
					batchNameCheckingOutputFtpBean.setRelationship_type_code(detailBe.getEntityRelationship());
				}
			}
			
			batchNameCheckingOutputFtpList.add(batchNameCheckingOutputFtpBean);
		}

		//2.處理未結案之Hit(過往)
		nameCheckRecordMainList = (List<NameCheckRecordMain>) iNameCheckRecordMainDao
				.findByInterfaceNameAndScreenProcessAndTransactionDateLessThanAndNcResult(interfaceName,
						ScreenProcess, Timestamp.valueOf(sdf.format(now) + " 00:00:00"), ncResultHit);
		for(NameCheckRecordMain mainBe : nameCheckRecordMainList){
			BatchNameCheckingOutputFtpBean batchNameCheckingOutputFtpBean  = new BatchNameCheckingOutputFtpBean();
			
			//處理存於Main檔中的資訊
			batchNameCheckingOutputFtpBean.setParty_number(mainBe.getPartyNumber());
			batchNameCheckingOutputFtpBean.setName_check_status(mainBe.getNcResult());
			batchNameCheckingOutputFtpBean.setName_check_date(mainBe.getTransactionDate());
			batchNameCheckingOutputFtpBean.setHit_route(mainBe.getRouteRule());
			
			//處理存於Detail檔中的資訊
			List<NameCheckRecordDetail> nameCheckRecordDetailList =  iNameCheckRecordDetailDao.findByIdUniqueKeyAndIdNcReferenceId(mainBe.getId().getUniqueKey(), mainBe.getId().getNcReferenceId());
			for(NameCheckRecordDetail detailBe : nameCheckRecordDetailList){
				String [] checkSeqSpilit = detailBe.getId().getCheckSeq().split("_!_");
				if(checkSeqSpilit.length > 1){  //代表有關係				
					batchNameCheckingOutputFtpBean.setRelated_party_number(batchNameCheckingOutputFtpBean.getParty_number());
					batchNameCheckingOutputFtpBean.setParty_number(checkSeqSpilit[1]);			
					batchNameCheckingOutputFtpBean.setRelationship_type_code(detailBe.getEntityRelationship());
				}
			}
			
			batchNameCheckingOutputFtpList.add(batchNameCheckingOutputFtpBean);
		}

		
		
		//3. 處理當日已結案  
		List<String> caseStatusCd = new ArrayList<String>();
		caseStatusCd.add("C");
		caseStatusCd.add("F");
		List<CaseVersion> caseVersionList = (List<CaseVersion>) iCaseVersionDao.findByCaseCategoryCdAndValidToDttmGreaterThanEqualAndCaseStatusCdIn(sourceTypeBtNc, Timestamp.valueOf(sdf.format(now) + " 00:00:00"), caseStatusCd);
		List<Long> caseRkList = new ArrayList<Long>();
		for(CaseVersion CaseVersionBe : caseVersionList){
			caseRkList.add(CaseVersionBe.getId().getCaseRk().longValue());
		}	
		nameCheckRecordMainList = (List<NameCheckRecordMain>)iNameCheckRecordMainDao.findByInterfaceNameAndScreenProcessAndCaseRkIn(interfaceName, ScreenProcess, caseRkList);
		for(NameCheckRecordMain mainBe : nameCheckRecordMainList){
			BatchNameCheckingOutputFtpBean batchNameCheckingOutputFtpBean  = new BatchNameCheckingOutputFtpBean();
			
			//處理存於Main檔中的資訊
			batchNameCheckingOutputFtpBean.setParty_number(mainBe.getPartyNumber());
			batchNameCheckingOutputFtpBean.setName_check_status(mainBe.getNcResult());
			batchNameCheckingOutputFtpBean.setName_check_date(mainBe.getTransactionDate());
			batchNameCheckingOutputFtpBean.setHit_route(mainBe.getRouteRule());
			
			//處理存於Detail檔中的資訊
			List<NameCheckRecordDetail> nameCheckRecordDetailList =  iNameCheckRecordDetailDao.findByIdUniqueKeyAndIdNcReferenceId(mainBe.getId().getUniqueKey(), mainBe.getId().getNcReferenceId());
			for(NameCheckRecordDetail detailBe : nameCheckRecordDetailList){
				String [] checkSeqSpilit = detailBe.getId().getCheckSeq().split("_!_");
				if(checkSeqSpilit.length > 1){  //代表有關係				
					batchNameCheckingOutputFtpBean.setRelated_party_number(batchNameCheckingOutputFtpBean.getParty_number());
					batchNameCheckingOutputFtpBean.setParty_number(checkSeqSpilit[1]);			
					batchNameCheckingOutputFtpBean.setRelationship_type_code(detailBe.getEntityRelationship());
				}
			}
			
			batchNameCheckingOutputFtpList.add(batchNameCheckingOutputFtpBean);
		}

		
		//寫入檔案於Web Server的Disk中
		String fileOutputPath = AmlConfiguration.getString("com.sas.upload.filePath.6");		
		String fileNameD = AmlConfiguration.getString("com.sas.ftp.ncbatch.filename") + sdfOutput.format(now) + ".d";
		String fileNameH = AmlConfiguration.getString("com.sas.ftp.ncbatch.filename") + sdfOutput.format(now) + ".h";
		try {
			// 批次內容檔
			PrintWriter pw = new PrintWriter(new File(fileOutputPath + fileNameD));
			StringBuilder sb = new StringBuilder();
			sb.append("party_number").append(",");
			sb.append("related_party_number").append(",");
			sb.append("relationship_type_code").append(",");
			sb.append("Name_check_status").append(",");
			sb.append("Hit Route").append(",");
			sb.append("Confirm Hit Route").append(",");
			sb.append("name_check_date").append("\n");
			
			for(BatchNameCheckingOutputFtpBean be :batchNameCheckingOutputFtpList){
				sb.append(be.getParty_number()).append(",");
				sb.append(be.getRelated_party_number()).append(",");
				sb.append(be.getRelationship_type_code()).append(",");
				sb.append(be.getName_check_status()).append(",");
				sb.append(be.getHit_route()).append(",");
				sb.append(be.getConfirm_hit_route()).append(",");
				sb.append(sdf.format(be.getName_check_date())).append("\n");
			}
			
			pw.write(sb.toString());
			pw.close();
			
			
			String dataStardDate = sdfOutput.format(now);
			String dataEndDate = sdfOutput.format(now);			
			String dataOutputDateTime = sdfDTOutput.format(now);
			
			
			//批次控制檔
			PrintWriter pw2 = new PrintWriter(new File(fileOutputPath + fileNameH));
			StringBuilder sb2 = new StringBuilder();
			sb2.append(batchNameCheckingOutputFtpList.size());
			pw2.write(dataStardDate);//資料起始日
			pw2.write(",");
			pw2.write(dataEndDate);//資料結束日
			pw2.write(",");
			pw2.write(fileNameD); //檔案檔名
			pw2.write(",");
			pw2.write(dataOutputDateTime); //轉出檔案時間
			pw2.write(",");
			pw2.write(sb2.toString()); //資料筆數
			pw2.close();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("BatchNameCheckingResultImpl-batchNameCheckingResultImpl ex=" +  e);
			e.printStackTrace();
		}
		
		
		//FTP上傳		

		FTPUtil ftpUtil = null;
		try{
			String ftpServer = AmlConfiguration.getString("com.sas.ftp.ncbatch.server");
			int ftpPort = Integer.valueOf(AmlConfiguration.getString("com.sas.ftp.ncbatch.port")).intValue();
			String ftpUser = AmlConfiguration.getString("com.sas.ftp.ncbatch.user");
			String ftpPwd = new String(Base64Utils.decodeFromString(AmlConfiguration.getString("com.sas.ftp.ncbatch.password")));		
			
			ftpUtil = new FTPUtil(ftpServer, ftpPort, ftpUser, ftpPwd);
			String ftpUploadFolder = AmlConfiguration.getString("com.sas.ftp.ncbatch.uploadFolder");
			
			//FTP 上傳 xxx.d檔
			ftpUtil.uploadFileToServer(fileOutputPath, fileNameD, ftpUploadFolder, fileNameD);
			
			//FTP 上傳 xxx.h檔
			ftpUtil.uploadFileToServer(fileOutputPath, fileNameH, ftpUploadFolder, fileNameH);
			
			
		}catch(Exception ex){
			logger.error(ex.toString(), ex);
		}finally{
			if(ftpUtil!=null){
				ftpUtil.close();
			}
		}

		batchNameCheckingResultOutputBean.setFinish(true);
		return batchNameCheckingResultOutputBean;
	}

}
