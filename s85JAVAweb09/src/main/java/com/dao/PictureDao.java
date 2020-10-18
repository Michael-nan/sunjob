package com.dao;

import com.pojo.Goods;
import com.pojo.Picture;
import com.util.DButil;

import java.util.List;

public class PictureDao {

    public List<Picture> findbyid(String goodsid) {
    String sql="select pid, pname, gid from picture where gid=?";
    List<Picture> list= DButil.query(sql,Picture.class,goodsid);
    return list;
    }
}
