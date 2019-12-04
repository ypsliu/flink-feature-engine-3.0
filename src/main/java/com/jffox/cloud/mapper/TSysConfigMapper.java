package com.jffox.cloud.mapper;

import com.jffox.cloud.domain.TSysConfig;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TSysConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TSysConfig record);

    int insertSelective(TSysConfig record);

    TSysConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TSysConfig record);

    int updateByPrimaryKey(TSysConfig record);
}