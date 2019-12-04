package com.jffox.cloud.examples;

import com.jffox.cloud.configs.MysqlConfig;
import com.jffox.cloud.domain.TSysCode;
import com.jffox.cloud.mapper.TSysCodeMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

@Slf4j
public class TestMysql {
    TSysCodeMapper tSysCodeMapper;

    public static void main(String[] args) {
        SqlSession session = MysqlConfig.getSqlSession();
        TSysCodeMapper tSysCodeMapper = session.getMapper(TSysCodeMapper.class);

        TSysCode tSysCode = tSysCodeMapper.selectByPrimaryKey("000");
        log.info(tSysCode.getCodeDesc());
        session.close();


    }
}
