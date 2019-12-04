package com.jffox.cloud.mapper;

import com.jffox.cloud.domain.TSysCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TSysCodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(TSysCode record);

    int insertSelective(TSysCode record);

    TSysCode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TSysCode record);

    int updateByPrimaryKey(TSysCode record);
}