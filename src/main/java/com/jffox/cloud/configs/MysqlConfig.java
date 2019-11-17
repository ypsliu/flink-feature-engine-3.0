package com.jffox.cloud.configs;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * local mysql 会话
 *
 * @author yanggang
 * @version 1.0
 * @date 2019-11-17
 * @describe local mysql 会话
 * @since jdk 1.8
 */
@Slf4j
public class MysqlConfig {

    private static SqlSessionFactory sqlSessionFactory = null;

    /**
     * 获取SqlSessionFactory
     * 用静态代码块进行创建SqlSessionFactory，只在类加载时创建一次，保证了整个程序运行时只有一个工厂实例。
     */
    static {
        InputStream in;
        try {
            in = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MysqlConfig() {
    }

    /**
     * 获取SqlSession
     *
     * @return SqlSession
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }


}

