package com.cn.dao;

import com.cn.entity.MessageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MessageInfoMapper {
    int deleteByPrimaryKey(Integer messageId);

    int insert(MessageInfo record);

    int insertSelective(MessageInfo record);

    MessageInfo selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(MessageInfo record);

    int updateByPrimaryKey(MessageInfo record);

    public List<MessageInfo> queryPageByConditons(@Param("map")Map<String, Object> map);

    public int queryPageCount(@Param("map")Map<String, Object> map);


}