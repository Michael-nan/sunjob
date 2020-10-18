package com.dao;

import com.pojo.UserInfo;
import com.util.DButil;

import java.util.List;

public class UserDao {
    public List<UserInfo> fenye(int page,int size){
        String sql="select username , password from userinfo limit ?,?";
        List<UserInfo> list= DButil.query(sql,UserInfo.class,(page-1)*size,size);
        return list;
    }

    public int getCount() {
        String sql="select count(*) from userinfo";
        //  unique double 所以要强转
        int count= (int) DButil.uniqueQuery(sql);
        return count;
    }

    public int deleteByUsername(String username) {
        String sql="delete from userinfo where username=?";
        int n=DButil.zsg(sql,username);
        return n;

    }
}
