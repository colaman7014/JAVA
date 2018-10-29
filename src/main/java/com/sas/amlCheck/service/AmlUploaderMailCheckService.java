package com.sas.amlCheck.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sas.amlCase.viewBean.MailUploadBean;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.ISendMailDao;
import com.sas.db.aml.orm.ecm.SendMail;
import com.sas.util.AmlConfiguration;
import com.sas.util.FTPUtil;

/**
 * @author MengChe
 */
@Service
public class AmlUploaderMailCheckService {
	
	@Autowired
	ISendMailDao iSendMailDao;
	
	public static void main(String[] args){
		AmlUploaderMailCheckService service = new AmlUploaderMailCheckService();
		service.checkMailReceiverFormat("xxxx@gmail.com.tw");
	}
	
//	@Transactional(transactionManager="amlTransactionManager", rollbackFor=java.net.ConnectException.class)
	@Transactional(transactionManager="amlTransactionManager", rollbackFor=java.lang.Exception.class )
	public MailUploadBean checkUploadMailFormat(MailUploadBean checkData, MultipartFile multipartFile) throws Exception{
		long currentTime = new Date().getTime();
		String filePath = "";
		String newFileName = "";
		String newFileLocation = "";

		if(multipartFile == null){
			checkData.setIsAttachement(SwiftMtConst.MAIL_EXIST_ATTACHEMENT_N);
		}else{
			filePath = AmlConfiguration.getString("com.sas.mail.upload.filePath");
			newFileName = String.format("%s_%s_%s", checkData.getSender(), currentTime,  multipartFile.getOriginalFilename());
			newFileLocation = String.format("%s%s", filePath, newFileName);

			checkData.setFileName(multipartFile.getName());
			checkData.setIsAttachement(SwiftMtConst.MAIL_EXIST_ATTACHEMENT_Y);
		}
		
		// To Set File Path
		checkData.setFilePath(newFileLocation);
		checkData.setFileName(newFileName);
		// To Set ProcessTime
		checkData.setProcessDate(new Timestamp(currentTime));
		
		// To Initial Error Message
		StringBuilder errMsg = new StringBuilder();
		
		// To Check [SOURCE]  
		if(StringUtils.isBlank(checkData.getSource()) || 
				checkData.getSource().length() > 74){
			errMsg.append(String.format(SwiftMtConst.CHECK_UPLOAD_MAIL_EMPTY_MSG, 
						  "Source", 74, SwiftMtConst.GUI_MSG_SPLIT));
		}
		// To Check [SUBJECT] 
		if(StringUtils.isBlank(checkData.getSubject()) ||
				checkData.getSubject().length() > 100){
			errMsg.append(String.format(SwiftMtConst.CHECK_UPLOAD_MAIL_EMPTY_MSG, 
			  			  "Subject", 100, SwiftMtConst.GUI_MSG_SPLIT));
		}
		
		// To Check [RECEIVER] 
		if(StringUtils.isBlank(checkData.getReceiver()) ||
				checkData.getReceiver().length() > 1024){
			errMsg.append(String.format(SwiftMtConst.CHECK_UPLOAD_MAIL_EMPTY_MSG, 
	  			  "Receiver", 1024, SwiftMtConst.GUI_MSG_SPLIT));
		}else{
			String receivers[] = checkData.getReceiver().split(SwiftMtConst.MAIL_RECEIVER_SPLIT);
			if(receivers.length > 0){
				for(String receiver : receivers){
					if(!checkMailReceiverFormat(receiver)){
						errMsg.append(String.format(SwiftMtConst.MAIL_ADDRESS_ERROR_MSG, 
								receiver, SwiftMtConst.GUI_MSG_SPLIT));
						break;
					}
				}
			}else{
				if(!checkMailReceiverFormat(checkData.getReceiver())){
					errMsg.append(String.format(SwiftMtConst.MAIL_ADDRESS_ERROR_MSG, 
							checkData.getReceiver(), SwiftMtConst.GUI_MSG_SPLIT));
				}
			}
		}
		
		// To Check [MAIL_CONTENT] 
		if(StringUtils.isBlank(checkData.getMailContent()) ||
				checkData.getMailContent().length() > 2000){
			errMsg.append(String.format(SwiftMtConst.CHECK_UPLOAD_MAIL_EMPTY_MSG, 
					  "Mail_content", 2000, SwiftMtConst.GUI_MSG_SPLIT));
		}
		
		// To Check [SENDER]
		if(checkData.getSender().length()>50){
			errMsg.append(String.format(SwiftMtConst.MAIL_LENGTH_ERROR_MSG, 
					"Sender", 50, SwiftMtConst.GUI_MSG_SPLIT));
		}
		
		// To Set Validate Status and Error Message For Gui
		if(!StringUtils.isBlank(errMsg.toString())){
			checkData.setFormatErr(true);
			checkData.setErrMsg(errMsg.toString());
		}else{
			checkData.setFormatErr(false);
			checkData.setErrMsg(StringUtils.EMPTY);
			// Insert Data To Database
			SendMail bean = new SendMail(checkData);
			iSendMailDao.save(bean);
			
			// Upload File To Web Server 

			if(multipartFile != null){
				// To Upload File Local Server
				multipartFile.transferTo(new File(newFileLocation));
				// To Upload File to FTP Server
				this.uploadFileToFTP(newFileName);
			}
		}
		return checkData;
	}
	
	// To Check Mail Address Format
	private boolean checkMailReceiverFormat(String receiver){
		Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile(SwiftMtConst.MAIL_ADDR_PATTERN, Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(receiver);
		return matcher.find();
	}
	
	// Upload File to FTP From Local 
	private void uploadFileToFTP(String fileName) throws Exception{
		// To Initial FTP
		FTPUtil ftpUtil = null; 
		try {
			ftpUtil = new FTPUtil();
			// To Get Local File Path
			String localPath = AmlConfiguration.getString("com.sas.mail.upload.filePath");
			// To Get Upload Folder In FTP Server
			String ftpUploadFolder = AmlConfiguration.getString("com.sas.mail.ftp.uploadFolder");
			ftpUtil.uploadFileToServer(localPath, fileName, ftpUploadFolder, fileName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception (e.getMessage());
		}
	}

}
