package org.sustcDB2019.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DaoManager {
    private static String resource = "/main/resources/mybatis-config.xml";
    //private static InputStream inputStream;// = Resources.getResourceAsStream(resource);
    //public static SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    private static DaoManager d = new DaoManager();
    public static SqlSessionFactory sqlSessionFactory;
    public DaoManager(){
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }catch (IOException e){
            e.getMessage();
        }
    }

    public static String modifyStringToFuzzyQuery(String str){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("'%");
        stringBuilder.append(str);
        stringBuilder.append("%'");
        String str1 = stringBuilder.toString();
        return str1;
    }

//    public static void main(String[] args){
//        try {
//            inputStream = Resources.getResourceAsStream(resource);
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        }catch (IOException e){
//            e.getMessage();
//        }
//
//    }
}
