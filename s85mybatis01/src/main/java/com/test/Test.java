package com.test;

import com.pojo.Dep;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test extends TestCase {
    public void _testFindAll() throws IOException {
        Reader reader= Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession=sqlSessionFactory.openSession();

        List<Dep> list=sqlSession.selectList("com.pojo.Dep.findall");

        for (Dep dep : list) {
            System.out.println(dep.getDepid()+"\t"+dep.getDepname());
        }
    }

    public void _testUpdate() throws IOException {
        Reader reader= Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession=sqlSessionFactory.openSession();

        Dep dep=new Dep();
        dep.setDepid(2);
        dep.setDepname("麻将部");

        int n=sqlSession.update("com.pojo.Dep.update",dep);
        sqlSession.commit();
        System.out.println(n);
    }

    public void testAdd() throws IOException {
        Reader reader=Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession=sqlSessionFactory.openSession();

        Dep dep=new Dep();
        dep.setDepid(2);
        dep.setDepname("宣传部");

        int n=sqlSession.insert("com.pojo.Dep.insert",dep);
        sqlSession.commit();
        System.out.println(n);
    }

    public void _testDelete() throws IOException {
        Reader reader=Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession=sqlSessionFactory.openSession();

        int n=sqlSession.delete("com.pojo.Dep.delete",2);
        sqlSession.commit();
        System.out.println(n);
    }
}
