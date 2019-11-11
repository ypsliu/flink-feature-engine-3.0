package com.jffox.cloud.utils;

import java.io.*;

/**
 * @author yanggang
 * @version 1.0
 * @date 2019-10-16
 * @describe 获取本地数据文件工具类
 * @since jdk 1.8
 */
public class GetLocalDataUtil {
    /**
     * @param path 文件路径
     * @return 文件内容
     */
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

    /**
     * @param args 参数
     * @describe 测试主方法
     */
    public static void main(String[] args) {
        GetLocalDataUtil dd = new GetLocalDataUtil();
        System.out.println(GetLocalDataUtil.get("c://aa.txt"));
    }
}
