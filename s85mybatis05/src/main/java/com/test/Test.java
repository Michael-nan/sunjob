package com.test;

import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.io.Reader;

public class Test extends TestCase {
    public void testFindDep() throws IOException {
        Reader reader= Resources.getResourceAsReader("mybatis-config.xml");
        //SqlSessionFactory
    }
}
