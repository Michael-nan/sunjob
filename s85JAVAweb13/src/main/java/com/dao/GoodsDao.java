package com.dao;

import com.pojo.Goods;
import com.util.DButil;

import java.util.ArrayList;
import java.util.List;

public class GoodsDao {

    public int getCount(String goodsname , String bottom , String top) {

        StringBuffer sb = new StringBuffer("select count(*) from goods where 1=1 ");

        List values = new ArrayList();

        if(goodsname!=null && goodsname.trim().length()>0){
            sb.append(" and goodsname like ? ");
            values.add("%"+goodsname+"%");
        }

        if(bottom!=null && bottom.trim().length()>0){
            sb.append(" and goodsprice >= ? ");
            values.add(bottom);
        }

        if(top!=null && top.trim().length()>0){
            sb.append(" and goodsprice <= ? ");
            values.add(top);
        }

        int n = (int) DButil.uniqueQuery(sb.toString() , values.toArray());
        return n;

    }

    public List<Goods> fenYe(int page, int size , String goodsname , String bottom , String top) {

        StringBuffer sb = new StringBuffer("select goodsid , goodsname , goodspic , goodscount , goodsprice , goodsinfo  from goods where 1=1 ");

        List values = new ArrayList();

        if(goodsname!=null && goodsname.trim().length()>0){
            sb.append(" and goodsname like ? ");
            values.add("%"+goodsname+"%");
        }

        if(bottom!=null && bottom.trim().length()>0){
            sb.append(" and goodsprice >= ? ");
            values.add(bottom);
        }

        if(top!=null && top.trim().length()>0){
            sb.append(" and goodsprice <= ? ");
            values.add(top);
        }

        sb.append(" limit ? , ?");
        values.add((page-1)*size);
        values.add(size);


        List<Goods> list = DButil.query(sb.toString(), Goods.class, values.toArray());
        return list;


    }
}
