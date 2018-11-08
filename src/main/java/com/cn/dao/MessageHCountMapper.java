package com.cn.dao;

import com.cn.entity.MessageHCount;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface MessageHCountMapper {
    int deleteByPrimaryKey(Integer hcountId);

    int insert(MessageHCount record);

    int insertSelective(MessageHCount record);

    MessageHCount selectByPrimaryKey(Integer hcountId);

    MessageHCount selectByEntity(MessageHCount record);

    int updateByPrimaryKeySelective(MessageHCount record);

    int updateByPrimaryKey(MessageHCount record);

    public int queryPageCount(@Param("map")Map<String, Object> map);
}