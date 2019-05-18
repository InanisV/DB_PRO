package com.sustcDB2019.DB104;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.sustcDB2019.dao.DaoManager;
import org.sustcDB2019.dao.DelivererMapper;
import org.sustcDB2019.entity.Deliverer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class sqlTest {
    private static SqlSessionFactory sqlSessionFactory;

    public sqlTest(){
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args){
        new sqlTest();


//        deliverer();
        test1();
    }

    public static void deliverer(){
        SqlSession session = sqlSessionFactory.openSession();

        DelivererMapper mapper = session.getMapper(DelivererMapper.class);
        Deliverer deliverer = new Deliverer();
        deliverer.setStatusOn("Y");
        deliverer.setWarehouseWarehouseId(6);
        ArrayList<Deliverer> arrayList = mapper.selectByCase(deliverer);
        for(Deliverer x: arrayList){
            System.out.println(x.getUserId().toString());
        }
    }

    public static void test1(){

    }

}
