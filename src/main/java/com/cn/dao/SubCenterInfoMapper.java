package com.cn.dao;

import com.cn.entity.SubCenterInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SubCenterInfoMapper {
    int deleteByPrimaryKey(Integer subcenterId);

    int insert(SubCenterInfo record);

    int insertSelective(SubCenterInfo record);

    SubCenterInfo selectByPrimaryKey(Integer subcenterId);

    int updateByPrimaryKeySelective(SubCenterInfo record);

    int updateByPrimaryKey(SubCenterInfo record);

    //public List<SubCenterInfo> selectByParams(SubCenterInfo record);

    public List<SubCenterInfo> queryPageByConditons(@Param("map")Map<String, Object> map);

    public int queryPageCount(@Param("map")Map<String, Object> map);
}