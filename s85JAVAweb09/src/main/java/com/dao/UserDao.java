package com.dao;

import com.pojo.Collection;
import com.pojo.UserInfo;
import com.util.DButil;

import java.util.List;

public class UserDao {

    public Collection check(String username, String goodsid) {
        String sql="select username ,goodsid from collection where username=? and goodsid=?";
        List<Collection> list= DButil.query(sql,Collection.class,username,goodsid);
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }

    public int addCollection(String username, String goodsid) {
        String sql="insert into collection(username,goodsid) value(?,?)";
        int n=DButil.zsg(sql,username,goodsid);
        return n;
    }

    public UserInfo login(String username, String password) {
        String sql="select username ,password from userinfo where username=?and password=?";
        List<UserInfo> list =DButil.query(sql,UserInfo.class,username,password);
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }

    public int zhuce(String username, String password) {
        String sql="insert into userinfo(username,password) value(?,?)";
        int n=DButil.zsg(sql,username,password);
        return  n;
    }


    public List<Collection> findbyusername(String username) {
        String sql="select username,goodsid from collection where username=?";
        List<Collection> list=DButil.query(sql,Collection.class,username);
        return list;
    }
}
