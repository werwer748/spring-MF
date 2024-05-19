package com.example.repository;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory; // SqlSession이 들어감 => 이 세션을 꺼내와서 DB로 요청함.

    static {
        try {
            // XML에서 SqlSessionFactory 빌드하기
            String resource = "mybatis-config/config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); // Connection(SqlSession)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openSession() { // MyBatisUtil.openSession()
        return sqlSessionFactory.openSession();
    }
}
