package com.sas.aml.retry.event.queue.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;

public class XRetryMailUtils {
	private static final Logger logger = LoggerFactory.getLogger(XRetryMailUtils.class);

	private static String host;
	private static int port;
	private static String username;
	private static String password;
	private static String sendFrom;
	private static String sendNick;
	private static String startTls;
	private static String enableSSL;
	private static String sendTo;

	static {
		host = (XRetryPropertyUtils.getString("retry.schedule.mail.host") == ""
				|| XRetryPropertyUtils.getString("retry.schedule.mail.host") == null) ? "smtp.gmail.com"
						: XRetryPropertyUtils.getString("retry.schedule.mail.host");
		port = (XRetryPropertyUtils.getString("retry.schedule.mail.port") == ""
				|| XRetryPropertyUtils.getString("retry.schedule.mail.port") == null) ? 587
						: Integer.parseInt(XRetryPropertyUtils.getString("retry.schedule.mail.port"));
		username = (XRetryPropertyUtils.getString("retry.schedule.mail.username") == ""
				|| XRetryPropertyUtils.getString("retry.schedule.mail.username") == null) ? ""
						: XRetryPropertyUtils.getString("retry.schedule.mail.username");
		password = (XRetryPropertyUtils.getString("retry.schedule.mail.password") == ""
				|| XRetryPropertyUtils.getString("retry.schedule.mail.password") == null) ? ""
						: XRetryPropertyUtils.getString("retry.schedule.mail.password");
		sendFrom = (XRetryPropertyUtils.getString("retry.schedule.mail.sendFrom") == ""
				|| XRetryPropertyUtils.getString("retry.schedule.mail.sendFrom") == null) ? "System"
						: XRetryPropertyUtils.getString("retry.schedule.mail.sendFrom");
		sendTo = (XRetryPropertyUtils.getString("retry.schedule.mail.sendTo") == ""
				|| XRetryPropertyUtils.getString("retry.schedule.mail.sendTo") == null) ? ""
						: XRetryPropertyUtils.getString("retry.schedule.mail.sendTo");
		sendNick = (XRetryPropertyUtils.getString("retry.schedule.mail.sendNick") == ""
				|| XRetryPropertyUtils.getString("retry.schedule.mail.sendNick") == null) ? "System"
						: XRetryPropertyUtils.getString("retry.schedule.mail.sendNick");
		startTls = (XRetryPropertyUtils.getString("retry.schedule.mail.tls.enable") == ""
				|| XRetryPropertyUtils.getString("retry.schedule.mail.tls.enable") == null) ? "false"
						: XRetryPropertyUtils.getString("retry.schedule.mail.tls.enable");
		enableSSL = (XRetryPropertyUtils.getString("retry.schedule.mail.ssl.enable") == ""
				|| XRetryPropertyUtils.getString("retry.schedule.mail.ssl.enable") == null) ? "false"
						: XRetryPropertyUtils.getString("retry.schedule.mail.ssl.enable");
	}

	public static boolean sendMail(String toAddress, String mailSubject, String mailBody, boolean mailBodyIsHtml,
			File[] attachments) {
		try {
			// 创建邮件发送类 JavaMailSender (用于发送多元化邮件，包括附件，图片，html 等 )
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost(host); // 设置邮件服务主机
			mailSender.setUsername(username); // 发送者邮箱的用户名
			mailSender.setPassword(password); // 发送者邮箱的密码

			// 配置文件，用于实例化java.mail.session
			Properties pro = new Properties();

			pro.put("mail.smtp.auth", "true");
			pro.put("mail.smtp.socketFactory.port", port);
			// Use the following if you need SSL
			if (enableSSL.equalsIgnoreCase("true")) {
				pro.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				pro.put("mail.smtp.socketFactory.fallback", enableSSL);
			}

			if (startTls.equalsIgnoreCase("true")) {
				pro.put("mail.smtp.starttls.enable", startTls);
			}

			mailSender.setJavaMailProperties(pro);

			// 创建多元化邮件 (创建 mimeMessage 帮助类，用于封装信息至 mimeMessage)
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, ArrayUtils.isNotEmpty(attachments), "UTF-8");

			helper.setFrom(sendFrom, sendNick);
			helper.setTo(toAddress);

			helper.setSubject(mailSubject);
			helper.setText(mailBody, mailBodyIsHtml);

			// 添加附件
			if (ArrayUtils.isNotEmpty(attachments)) {
				for (File file : attachments) {
					helper.addAttachment(MimeUtility.encodeText(file.getName()), file);
				}
			}

			mailSender.send(mimeMessage);
			return true;
		} catch (Exception e) {
			logger.error("sendMail Error : " + e.getMessage());
		}
		return false;
	}

	public static boolean sendMails(String[] toAddress, String mailSubject, String mailBody, boolean mailBodyIsHtml,
			File[] attachments) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			if (username != null || password != null) {
				props.put("mail.smtp.auth", "true");
			} else {
				props.put("mail.smtp.auth", "false");
			}
			
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sendFrom));
			if(toAddress.length > 0) {
				InternetAddress[] address = new InternetAddress[toAddress.length];
			    for (int i = 0; i < toAddress.length; i++) {
			        address[i] = new InternetAddress(toAddress[i]);
			    }
			    message.setRecipients(Message.RecipientType.TO, address);
			} else {
				ArrayList<InternetAddress> recipientsArray = new ArrayList<>();
				StringTokenizer st = new StringTokenizer(sendTo, ",");
				while (st.hasMoreTokens()) {
					recipientsArray.add(new InternetAddress(st.nextToken()));
				}

				int sizeTo = recipientsArray.size();
				InternetAddress[] addressTo = new InternetAddress[sizeTo];
				addressTo = (InternetAddress[]) recipientsArray.toArray();
				message.setRecipients(Message.RecipientType.TO, addressTo);
			}
			
			if (enableSSL.equalsIgnoreCase("true")) {
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.socketFactory.fallback", enableSSL);
			}

			if (startTls.equalsIgnoreCase("true")) {
				props.put("mail.smtp.starttls.enable", startTls);
			}
			
			message.setSubject(mailSubject);
			if(mailBodyIsHtml) {
				message.setContent(mailBody, "text/html; charset=utf-8");
			} else {
				message.setText(mailBody);

			}
			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, password);
			Transport.send(message);
			logger.info("Mail send to " + Arrays.toString(toAddress) +" successfully.");
		} catch (Exception e) {
			logger.error("sendMail Error : " + e.getMessage());
		}
		return false;
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 1; i++) {
			exec.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					String mailBody = "<html><head><meta http-equiv=" + "Content-Type" + " content="
							+ "text/html; charset=gb2312"
							+ "></head><body><h1>Test</h1>111<a href=''>222"
							+ "</a>333</body></html>";
					String mails = "dannielchung@gmail.com,nba.golden.curry@gmail.com";
					String[] email =  mails.split(",");
					sendMails(email, "Test Mail", mailBody, false, null);
				}
			}));
		}
	}

}
