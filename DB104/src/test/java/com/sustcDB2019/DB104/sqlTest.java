package com.sustcDB2019.DB104;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.sustcDB2019.dao.DaoManager;
import org.sustcDB2019.dao.DelivererMapper;
import org.sustcDB2019.dao.SalesMapper;
import org.sustcDB2019.entity.Deliverer;
import org.sustcDB2019.entity.Sales;

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
//        test1();
        int i = 30001302;
        ArrayList<Sales> s = showCart(i);
        for(Sales x: s){
            System.out.println( x.getSalesId().toString());
        }
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

    public static ArrayList<Sales> showCart(Integer userId) {
        SqlSession session = sqlSessionFactory.openSession();
        SalesMapper salesMapper = session.getMapper(SalesMapper.class);
        Sales sale = new Sales();
        sale.setCustomerUserId(userId);
        sale.setIsPaid("N");
        ArrayList<Sales> result = salesMapper.selectByCase(sale);
        return result;
    }

}
