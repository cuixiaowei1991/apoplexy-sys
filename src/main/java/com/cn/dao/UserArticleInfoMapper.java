package com.cn.dao;

import com.cn.entity.MessageInfo;
import com.cn.entity.UserArticleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserArticleInfoMapper {
    int deleteByPrimaryKey(Integer userarticleId);

    int insert(UserArticleInfo record);

    int insertSelective(UserArticleInfo record);

    UserArticleInfo selectByEntity(UserArticleInfo record);

    int updateByPrimaryKeySelective(UserArticleInfo record);

    int updateByPrimaryKey(UserArticleInfo record);

    public List<UserArticleInfo> queryPageByConditons(@Param("map")Map<String, Object> map);

    public int queryPageCount(@Param("map")Map<String, Object> map);
}