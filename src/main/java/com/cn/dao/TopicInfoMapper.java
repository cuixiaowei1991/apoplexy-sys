package com.cn.dao;

import com.cn.entity.MessageInfo;
import com.cn.entity.TopicInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TopicInfoMapper {
    int deleteByPrimaryKey(Integer topicId);

    int insert(TopicInfo record);

    int insertSelective(TopicInfo record);

    TopicInfo selectByPrimaryKey(Integer topicId);

    int updateByPrimaryKeySelective(TopicInfo record);

    int updateByPrimaryKey(TopicInfo record);

    public List<TopicInfo> queryPageByConditons(@Param("map")Map<String, Object> map);

    public int queryPageCount(@Param("map")Map<String, Object> map);
}