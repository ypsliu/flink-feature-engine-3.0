package com.jffox.cloud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResourceUtil {

    public static void main(String[] args) {
        System.out.println(ResourceUtil.getString("test"));

    }


    private final static Logger logger = LoggerFactory
            .getLogger(ResourceUtil.class);
    //    private static String localPath = System.getProperty("user.dir") + "/";
    private static String localPath = "/app/opt/appuser/config/";
    private static Properties props;

    public static void init() {

        logger.info("Loading properties started...");
        loadProperties("config.properties");
        logger.info("Loading properties ended...");
    }

    public static void reLoadProperties() {

    }

//    private static void loadProperties(String path) {
//        if (props == null) {
//            props = new Properties();
//        }
//        if (!StringUtil.isBlank(path)) {
//            Reader reader = null;
//            try {
//                if (StringUtil.isWindowOS()) {
//                    localPath = "D:/";
//                }
//                reader = new InputStreamReader(new FileInputStream(localPath
//                        + path), "UTF-8");
//                props.load(reader);
//            } catch (IOException e) {
//                logger.error("Load properties error...\n{}",
//                        ExceptionUtil.get(e));
//            } finally {
//                try {
//                    if (reader != null) {
//                        reader.close();
//                    }
//                } catch (IOException e) {
//                    logger.error("close file error...\n{}",
//                            ExceptionUtil.get(e));
//                }
//            }
//        } else {
//            throw new RuntimeException("Properties path is null");
//        }
//    }

    private static void loadProperties(String path) {
        if (props == null) {
            props = new Properties();
        }
        if (!StringUtil.isBlank(path)) {
            try {
                if (StringUtil.isWindowOS()) {
                    localPath = "D:/";
                    File file = new File(localPath);
                    if(!file.exists()){
                        localPath="C:\\app\\config\\";
                    }

                    Reader reader = null;
                    reader = new InputStreamReader(new FileInputStream(localPath
                            + path), "UTF-8");

                    props.load(reader);
                    reader.close();
                } else {
                    ClassPathResource classPathResource = new ClassPathResource("config.properties");
                    InputStream inputStream = classPathResource.getInputStream();
                    props.load(inputStream);
                    inputStream.close();
                }

            } catch (IOException e) {
                logger.error("Load properties error...\n{}",
                        ExceptionUtil.get(e));
            }
//            finally {
//                try {
//                    if (reader != null) {
//                        reader.close();
//                    }
//                } catch (IOException e) {
//                    logger.error("close file error...\n{}",
//                            ExceptionUtil.get(e));
//                }
//            }
        } else {
            throw new RuntimeException("Properties path is null");
        }
    }

    private static Properties getProperties() {
        if (props == null) {
            init();
            // throw new RuntimeException("Properties not initialized...");
        }
        return props;
    }

    public static Integer getInt(String name) {
        String property = getProperties().getProperty(name);
        if (property == null) return null;
        return Integer.parseInt(property);
    }

    public static long getLong(String name) {
        return Long.parseLong(getProperties().getProperty(name));
    }

    public static String getString(String name) {
        return getProperties().getProperty(name);
    }

    public static String getString(String name, String defaultValue) {
        return getProperties().getProperty(name, defaultValue);
    }

    public static Map<String, Object> loadPropertiesToMap(String path) {
        if (!StringUtil.isBlank(path)) {
            Reader reader = null;
            try {
                if (StringUtil.isWindowOS()) {
                    localPath = "D:/";
                }
                Properties properties = new Properties();
                reader = new InputStreamReader(new FileInputStream(localPath + path), "UTF-8");
                properties.load(reader);
                Map<String, Object> map = new HashMap<>();
                for (String key : properties.stringPropertyNames()) {
                    Object value = properties.getProperty(key).trim();
                    map.put(key, value);
                }
                return map;
            } catch (IOException e) {
                logger.error("Load properties error...\n{}", ExceptionUtil.get(e));
                throw new RuntimeException(e);
            } finally {

                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    logger.error("close file error...\n{}", ExceptionUtil.get(e));
                }
            }
        } else {
            logger.error("Properties path should not be empty.", path);
            throw new RuntimeException();
        }
        //return null;
    }
}
