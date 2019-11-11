package com.jffox.cloud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author yanggang
 * @version 1.0
 * @date 2018-04-12
 * @describe MD5工具类
 * @since jdk 1.8
 */

public class Md5Util {
    private static Logger logger = LoggerFactory.getLogger(Md5Util.class);
    public static MessageDigest digest = null;
    public static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    static {
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param str 输入字符串
     * @return 输出md5码值
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] btInput = str.getBytes();
        digest.reset();
        digest.update(btInput);
        byte[] md = digest.digest();
        // 把密文转换成十六进制的字符串形式
        int j = md.length;
        char strChar[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            strChar[k++] = hexDigits[byte0 >>> 4 & 0xf];
            strChar[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(strChar);
    }

    /**
     * 获取rowKey，MD5（str）前五位+“_”+str
     *
     * @param str
     * @return
     */
    public static String getRowKey(String str) {
        return getMd5Prefix5(str) + "_" + str;
    }

    /**
     * 获取md5(str)前五位
     *
     * @param str
     * @return
     */
    public static String getMd5Prefix5(String str) {
        String passMd5 = null;
        String rowKey = null;
        try {
            passMd5 = Md5Util.md5(str);
            rowKey = passMd5.substring(0, 5);
        } catch (UnsupportedEncodingException e) {
            logger.error("getRowKey error", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("getRowKey error", e);
        }
        return rowKey;
    }

    public static void main(String[] args) {
        String passMd5 = getRowKey("35176098");
        System.out.println(passMd5);
    }
}
