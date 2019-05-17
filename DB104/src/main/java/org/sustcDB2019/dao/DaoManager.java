package org.sustcDB2019.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.sustcDB2019.entity.User;

import java.io.IOException;
import java.io.InputStream;

public class DaoManager {
    private static String resource = "mybatis-config.xml";
    public static SqlSessionFactory sqlSessionFactory;
    public DaoManager(){
//        try {
//            InputStream inputStream = Resources.getResourceAsStream(resource);
//            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        }catch (IOException e){
//            e.getMessage();
//        }
    }

    public static String modifyStringToFuzzyQuery(String str){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("'%");
        stringBuilder.append(str);
        stringBuilder.append("%'");
        String str1 = stringBuilder.toString();
        return str1;
    }


}
