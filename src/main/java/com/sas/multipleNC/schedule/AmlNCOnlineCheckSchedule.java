package com.sas.multipleNC.schedule;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.sas.util.AmlConfiguration;
import com.sas.util.FileUtils;

@Component
public class AmlNCOnlineCheckSchedule {
	private static final Logger logger = LoggerFactory.getLogger(AmlNCOnlineCheckSchedule.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Integer count1 = 1;

//	@Scheduled(cron = "0 0 5 1 3 ?") //參考 Cron 表達式
	public void RemoveExpireFileSchdule() {
		try {
//			System.out.println(String.format("=== RemoveExpireFileSchdule 第 %s次執行，當前時間：%s", count1++, dateFormat.format(new Date())));
			logger.info(String.format("=== RemoveExpireFileSchdule 第 %s次執行，當前時間：%s", count1++, dateFormat.format(new Date())));
			int[] uploadTypes = new int[]{7, 8, 9}; 
			for (int uploadType : uploadTypes) {
				String filePath = AmlConfiguration.getString(String.format("com.sas.upload.filePath.%s", uploadType));
				FileUtils.deleteFilesOlderThanNdays(360, filePath);
			}
		} catch (Exception e) {
			logger.error(String.format("RemoveExpireFileSchdule Fail, Exception : %s", e.getMessage()), e);
		}
	}

	public static void main(String[] args) throws IOException {
		File file = new File("c:/temp/images.jpg");
		System.out.println(file + " creation time :" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
				.format(com.sas.util.FileUtils.getCreationTime(file).toMillis()));
	}
}
