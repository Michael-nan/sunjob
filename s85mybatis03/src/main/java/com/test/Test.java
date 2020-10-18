package com.test;

import com.pojo.Emp;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test extends TestCase{
    public void _testUpdate() throws IOException {
        Reader reader= Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession=sqlSessionFactory.openSession();

        Emp emp=new Emp();
        emp.setEmpid(1);
        emp.setEmpname("小住");

        int n= sqlSession.update("com.pojo.Emp.update",emp);
        sqlSession.commit();
        sqlSession.close();
    }

    public void testFindBy() throws IOException {
        Reader reader=Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession=sqlSessionFactory.openSession();

        Emp emp=new Emp();
        emp.setEmpid(1);

        List<Emp> list=sqlSession.selectList("com.pojo.Emp.findby",emp);
        for (Emp e : list) {
            System.out.println(e.getEmpid()+"\t"+e.getEmpname()+"\t"+e.getDepid());
        }
    }
}
