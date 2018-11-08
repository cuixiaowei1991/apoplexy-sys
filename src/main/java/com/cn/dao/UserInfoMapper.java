package com.cn.dao;

import com.cn.entity.MessageInfo;
import com.cn.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userinfoId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userinfoId);

    UserInfo selectByUserInfo(UserInfo userinfo);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    public List<UserInfo> queryPageByConditons(@Param("map")Map<String, Object> map);

    public int queryPageCount(@Param("map")Map<String, Object> map);
}