package com.dao;

import com.pojo.UserInfo;
import com.util.DButil;

import java.util.List;

public class UserDao {
    public UserInfo check(String username,String password){
        String sql="select username, password from userinfo where username=? and password=?";
        List<UserInfo> list= DButil.query(sql,UserInfo.class,username,password);
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }
}
