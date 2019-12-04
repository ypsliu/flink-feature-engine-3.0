package com.jffox.cloud.utils;

import java.io.*;

public class GetLocalDataUtil {
	public static String get(String path) {
		String laststr = "";
		File file = new File(path);
		BufferedReader reader = null;
		try {
			FileInputStream in = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException el) {
				}
			}
		}
		return laststr;
	}

	public static void main(String[] args) {
		GetLocalDataUtil dd = new GetLocalDataUtil();
		System.out.println(GetLocalDataUtil.get("c://aa.txt"));
	}
}
