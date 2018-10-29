/**
 * 
 */
package com.sas.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import com.sas.constraint.SwiftMtConst;

/**
 * FTP client util
 * 
 * @author sas
 *
 */
public class FTPUtil implements Closeable {

	private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);
	private static final String UTF8_BOM = "\uFEFF";  
	private FTPClient ftpClient;

	public FTPUtil(String ftpServer, int ftpPort, String ftpUser, String ftpPwd) throws Exception {
		ftpClient = new FTPClient();
		ftpClient.connect(ftpServer, ftpPort);
		if (!ftpClient.login(ftpUser, ftpPwd)) {
			logger.error("Login failed: " + ftpClient.getReplyString());
			throw new Exception("Login failed: " + ftpClient.getReplyString());
		}
		ftpClient.enterLocalPassiveMode();
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		logger.info("init ftp client success");
	}
	public FTPUtil() throws Exception {
		String ftpServer = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_SERVER);
		int ftpPort = Integer.valueOf(AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_PORT)).intValue();
		String ftpUser = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_USER);
		String ftpPwd = new String(Base64Utils.decodeFromString(AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_PASSWORD)));
		ftpClient = new FTPClient();
		ftpClient.connect(ftpServer, ftpPort);
		if (!ftpClient.login(ftpUser, ftpPwd)) {
			logger.error("Login failed: " + ftpClient.getReplyString());
			throw new Exception("Login failed: " + ftpClient.getReplyString());
		}
		ftpClient.enterLocalPassiveMode();
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		logger.info("init ftp client success");
	}

	public FTPFile[] getRemoteFileList(String remoteURI) throws IOException {
		FTPFile[] ftpFiles = null;
		try {
			ftpFiles = ftpClient.listFiles(remoteURI);
		} catch (IOException e) {
			logger.error(e.toString(), e);
			throw e;
		}
		return ftpFiles;
	}

	public boolean getRemoteFile(String remoteURI, String localURI) throws Exception {
		boolean result = false;

		logger.info("remoteFile:" + remoteURI);
		logger.info("downloadFile:" + localURI);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = ftpClient.retrieveFileStream(remoteURI);
			int reply = ftpClient.getReplyCode();
			if (inputStream == null
					|| (!FTPReply.isPositivePreliminary(reply) && !FTPReply.isPositiveCompletion(reply))) {

				throw new Exception(ftpClient.getReplyString());
			}
			File outputFile = new File(localURI);
			outputFile.createNewFile(); 
			outputStream = new BufferedOutputStream(new FileOutputStream(outputFile,false));

			byte[] bytesArray = new byte[1024];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(bytesArray)) > 0) {
				outputStream.write(bytesArray, 0, bytesRead);
				outputStream.flush();
			}
			
			boolean success = ftpClient.completePendingCommand();
			if (success) {
				logger.info(outputFile.getName() + " is downloaded successfully.");
			} else {
				logger.error("remoteFile download faild, remoteFile:" + remoteURI + ", downloadFile:" + localURI
						+ ", replyCode:" + ftpClient.getReplyCode() + ", replyString:" + ftpClient.getReplyString());
				throw new Exception("ftp download file failed");
			}

			result = true;
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw e;
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
			inputStream.close();
			outputStream.close();
//			close();
		}

		return result;
	}

	@Override
	public void close() {
		try {
			if (ftpClient.isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public boolean uploadFileToServer(String sourcePath, String sourceFileName, String remotePath,
			String remoteFileName) {
		boolean result = false;
		File localFile = new File(sourcePath + "/" + sourceFileName);
		String remoteFile = remotePath + "/" + remoteFileName;

		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(localFile);
			logger.info("Start uploading file");
			boolean uploadStaus = ftpClient.storeFile(remoteFile, inputStream);
			if (uploadStaus == false) {
				throw new Exception(ftpClient.getReplyString());
			}

			result = true;
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		logger.info("The file is uploaded successfully.");
		return result;
	}

	public boolean deleteRemoteFile(String ftpUri) {
		boolean deleted = false;
		logger.info("deleteRemoteFile:" + ftpUri);
		try {
			deleted = ftpClient.deleteFile(ftpUri);
			if (deleted) {
				logger.info("The file: " + ftpUri + " was deleted successfully.");
			} else {
				logger.error("Could not delete the file:" + ftpUri);
			}
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}

		return deleted;
	}

	public static Charset detectCharset(File f, String[] charsets) {
		Charset charset = null;
		for (String charsetName : charsets) {
			charset = detectCharset(f, Charset.forName(charsetName));
			if (charset != null) {
				break;
			}
		}
		return charset;
	}

	private static Charset detectCharset(File f, Charset charset) {
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(f));

			CharsetDecoder decoder = charset.newDecoder();
			decoder.reset();

			byte[] buffer = new byte[512];
			boolean identified = false;
			while ((input.read(buffer) != -1) && (!identified)) {
				identified = identify(buffer, decoder);
			}

			input.close();

			if (identified) {
				return charset;
			} else {
				return null;
			}

		} catch (Exception e) {
			return null;
		}
	}

	private static boolean identify(byte[] bytes, CharsetDecoder decoder) {
		try {
			decoder.decode(ByteBuffer.wrap(bytes));
		} catch (CharacterCodingException e) {
			return false;
		}
		return true;
	}

	public static String removeUTF8BOM(String line) {
		if (line.startsWith(UTF8_BOM)) {
			line = line.substring(1); // 如果 String 是以 BOM 開頭, 則省略字串最前面的第一個 字元.
		}
		return line;
	}
	
	
}
