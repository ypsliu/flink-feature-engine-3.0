package com.jffox.cloud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public final class ExceptionUtil {
	/**
	 * Gets all the exception stack information
	 *
	 * @param t
	 * @return String
	 */
	private static Logger LOG = LoggerFactory.getLogger(ExceptionUtil.class);
	private static String logFile = ResourceUtil.getString("error.data.dir");

	public static String get(Throwable t) {
		if (t instanceof GlobalCustomException) {
			StringBuilder sb = new StringBuilder();
			sb.append(t.getMessage());
			Throwable cause = t.getCause();
			if (cause != null){
				sb.append(getStackTrack(cause));
			}
			return sb.toString();
		}else {
			return getStackTrack(t);
		}
	}

	public static String getStackTrack(Throwable t){
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			t.printStackTrace(ps);
			return baos.toString();
		} catch (Exception e) {
			LOG.error("Writing stacktrace error", e);
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					LOG.error("Writing stacktrace error", e);
				}
				baos = null;
			}
		}
		return null;
	}

	public static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * Write error data to a log file
	 */
	public static void writeToLocalFile(Throwable t, String record) {
		String dateFormat = "[ERROR] [" + df.format(new Date())+ "] ";
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(logFile + "." + new SimpleDateFormat("yyyy-MM-dd").format(new Date()), true);
			fileWriter.write(dateFormat + get(t) + " Record =>" + record + System.lineSeparator());
			fileWriter.flush();
		} catch (IOException e) {
			LOG.error("Writing error data to local file error.", e);
		} finally {
			if (null != fileWriter) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					LOG.error("Close file writer error:", e);
				}
			}
		}
	}

}