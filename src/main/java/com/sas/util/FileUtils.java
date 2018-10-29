package com.sas.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * FileUtils {檔案操作的工具類 }
 * 
 * @author Eric Su
 * 
 */
public class FileUtils {
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	public static String getExtension(File file)
    {
        int startIndex = file.getName().lastIndexOf(46) + 1;
        int endIndex = file.getName().length();
        return  file.getName().substring(startIndex, endIndex);
    }
	
	public static void deleteFileOlderThanNdays(int daysBack, File file) {
		if (file == null)
			throw new IllegalArgumentException("No specified file");

		if (!file.exists()) {
			logger.warn("File not exists: " + file);
			return;
		}

		if (file.isDirectory()) {
			throw new IllegalArgumentException("File actually is a directory: " + file);
		}

		long purgeTime = System.currentTimeMillis() - (daysBack * 24 * 60 * 60 * 1000);
		if (file.lastModified() >= purgeTime)
			return;

		if (file.isFile()) {
			if (!file.delete()) {
				logger.error("Unable to delete file: " + file);
			}
		}
	}

	public static void deleteFilesOlderThanNdays(int daysBack, String dirWay) {
		File directory = new File(dirWay);
		deleteFilesOlderThanNdays(daysBack, directory);
	}

	public static void deleteFilesOlderThanNdays(int daysBack, File directory) {
		if (directory == null)
			throw new IllegalArgumentException("No specified directory");

		if (!directory.exists()) {
			logger.warn("Directory not exists: " + directory);
			return;
		}

		if (directory.isFile()) {
			throw new IllegalArgumentException("Directory actually is a file: " + directory);
		}

		File[] listFiles = directory.listFiles();
		if (listFiles == null) {
			logger.warn("No files in directory");
			return;
		}

		long purgeTime = System.currentTimeMillis() - (daysBack * 24 * 60 * 60 * 1000);
		for (File listFile : listFiles) {
			if (listFile.isFile()) {
				if (listFile.lastModified() < purgeTime) {
					if (!listFile.delete()) {
						logger.error("Unable to delete file: " + listFile);
					}
				}
			} else if (listFile.isDirectory()) {
				deleteFilesOlderThanNdays(daysBack, listFile);
			}
		}
	}
	
	public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
	   String mineType = servletContext.getMimeType(fileName);
	    try {
	        MediaType mediaType = MediaType.parseMediaType(mineType);
	        return mediaType;
	    } catch (Exception e) {
	        return MediaType.APPLICATION_OCTET_STREAM;
	    }
	}	
	
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            logger.info("listFilesForFolder :::" + fileEntry.getName());
	        }
	    }
	}
	
	public static FileTime getCreationTime(File file) throws IOException {
		Path p = Paths.get(file.getAbsolutePath());
		BasicFileAttributes view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();
		FileTime fileTime = view.creationTime();
		// also available view.lastAccessTine and view.lastModifiedTime
		return fileTime;
	}
}
