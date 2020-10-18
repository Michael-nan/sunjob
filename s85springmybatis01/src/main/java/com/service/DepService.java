package com.service;

import com.mapper.DepMapper;
import com.pojo.Dep;

import java.util.List;

public class DepService {
    private DepMapper depMapper;

    public DepMapper getDepMapper() {
        return depMapper;
    }

    public void setDepMapper(DepMapper depMapper) {
        this.depMapper = depMapper;
    }

    public List<Dep> findall(){
        List<Dep> list=depMapper.findall();
        return list;
    }
}
