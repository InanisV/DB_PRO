package com.sustcDB2019.DB104;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.sustcDB2019.dao.*;
import org.sustcDB2019.entity.*;

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

        test3();
//        deliverer();
//        test1();
//        int i = 30001302;
//        ArrayList<Sales> s = showCart(i);
//        for(Sales x: s){
//            System.out.println( x.getSalesId().toString());
//        }
    }

    public static void deliverer(){
        SqlSession session = sqlSessionFactory.openSession();

        DelivererMapper mapper = session.getMapper(DelivererMapper.class);
        Deliverer deliverer = new Deliverer();
        deliverer.setStatusOn("N");
        deliverer.setUserId(6000001);
        ArrayList<Deliverer> arrayList = mapper.selectByCase(deliverer);
        System.out.println(arrayList.size());
//        if(arrayList.size()){
//            System.out.println("null");
//        }
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

    public static void test3(){
        SqlSession session = sqlSessionFactory.openSession();
        WarehouseMapper mapper = session.getMapper(WarehouseMapper.class);
        Integer i = mapper.getRefriRestVolume(3);
        System.out.println(i.toString());
    }

    public static void test4(){
        SqlSession session = sqlSessionFactory.openSession();
        ManagerMapper managerMapper = session.getMapper(ManagerMapper.class);
        Manager manager = new Manager();
        manager.setUserId(2000001);
        manager.setWarehouseWarehouseId(10);

        int i = managerMapper.updateByPrimaryKey(manager);
        System.out.println("Yes "+i);
        session.close();
    }

    public static void test5(){
        SqlSession session = sqlSessionFactory.openSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        Order order = new Order();
        order.setCustomerUserId(30000001);
        orderMapper.selectByCase(order);
    }

    public static void test6(){
        SqlSession session = sqlSessionFactory.openSession();
        WarehouseMapper warehouseMapper = session.getMapper(WarehouseMapper.class);
        ArrayList<Warehouse> w = warehouseMapper.selectAll();
        for(Warehouse x: w){
            System.out.println("y");
            System.out.println(x.getWarehouseLati().toString());
        }
    }
}
