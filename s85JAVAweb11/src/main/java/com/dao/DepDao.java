package com.dao;

import com.pojo.Dep;
import com.util.DButil;

import java.util.List;

public class DepDao {

    public List<Dep> findAll(){
        String sql = "select depid , depname from dep";
        List<Dep> list = DButil.query(sql , Dep.class);
        return list ;
    }


}
