package com.dao;

import com.pojo.UserInfo;
import com.util.DButil;

import java.util.List;

public class UserDao {
    public List<UserInfo> findall(){
        String sql="select username ,password from userinfo";
        List<UserInfo> list= DButil.query(sql,UserInfo.class);
        return list;
    }
}
