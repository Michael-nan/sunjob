package com.dao;

import com.pojo.Goods;
import com.util.DButil;

import java.util.List;

public class GoodsDao {
    public Goods findbyid(String goodsid) {
        String sql="select goodsid, goodsname, goodspic ,goodscount ,goodsprice ,goodsinfo from goods where goodsid=? ";
        List<Goods> list=DButil.query(sql,Goods.class,goodsid);
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }

    public int getCount(){
        String sql="select count(*) from goods";
        int n= (int) DButil.uniqueQuery(sql);
        return n;
    }

    public List<Goods> fenye(int page, int size) {
    String sql="select goodsid, goodsname, goodspic ,goodscount ,goodsprice ,goodsinfo from goods limit ?,? ";
    List<Goods> list=DButil.query(sql,Goods.class,(page-1)*size,size);
    return list;
    }
}
