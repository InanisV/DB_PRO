package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.CustomerMapper;
import org.sustcDB2019.dao.DelivererMapper;
import org.sustcDB2019.dao.OrderMapper;
import org.sustcDB2019.dao.UserMapper;
import org.sustcDB2019.entity.Customer;
import org.sustcDB2019.entity.Deliverer;
import org.sustcDB2019.entity.Order;
import org.sustcDB2019.entity.User;

import java.util.ArrayList;
import java.util.Date;

public class DelivererService extends UserService {
    public Deliverer deliverer= new Deliverer();

    /*
    ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    ↓↓↓↓↓↓if this method is never used, delete it.↓↓↓↓↓
    ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public int setStatus(String status) {// dont need to change the DB?
        deliverer.setStatusOn(status);
        return 0;
    }
    /*
    ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    ↑↑↑↑↑↑if this method is never used, delete it.↑↑↑↑↑
    ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
     */

    public Customer getCurrentCustomer(int orderId){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        CustomerMapper customerMapper=sqlSession.getMapper(CustomerMapper.class);
        OrderMapper orderMapper=sqlSession.getMapper(OrderMapper.class);

        Order tmpOrder=orderMapper.selectByPrimaryKey(orderId);
        Customer tmpCustomer= customerMapper.selectByPrimaryKey(tmpOrder.getCustomerUserId());

        sqlSession.commit();
            sqlSession.close();
        return tmpCustomer;
    }

    public ArrayList<Order> getOrder(){
        SqlSession session=DAOService.sqlSessionFactory.openSession();
        OrderMapper mapper=session.getMapper(OrderMapper.class);

        Order tmpOrder=new Order();
        tmpOrder.setDeliveryUserId(deliverer.getUserId());
        ArrayList<Order> list=mapper.selectByCase(tmpOrder);//[add mapper]

        session.close();
        return list;
    }
    public ArrayList<Order> getCurrentOrder(){
        ArrayList<Order> tmpList=getOrder();
        ArrayList<Order> tmpList1=new ArrayList<>();
        for (Order order:tmpList) {
            if (order.getArrivalTime()==null){
                tmpList1.add(order);
            }

        }
        return tmpList1;
    }

    public int updateDeliverer(Deliverer deliverer){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        DelivererMapper mapper=sqlSession.getMapper(DelivererMapper.class);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        User tmpUser=new User(deliverer);

        if (tmpUser.getPassword()!=null&&tmpUser.getUserName()!=null&&tmpUser.getPhoneNumber()!=null){
            userMapper.updateByPrimaryKeySelective(tmpUser);
            sqlSession.commit();
        }

        if (deliverer.getWarehouseWarehouseId()!=null){
            mapper.updateByPrimaryKeySelective(deliverer);
        }

        sqlSession.commit();
            sqlSession.close();
        return 0;
    }

    public int orderDepart(Date currentDate){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        ArrayList<Order> list=getCurrentOrder();
        if (list.size()==0){
            sqlSession.commit();
            sqlSession.close();
            return 1;
        }
        for (Order order:list) {
            order.setDepartureTime(currentDate);
            orderMapper.updateByPrimaryKeySelective(order);
        }

        sqlSession.commit();
            sqlSession.close();
        return 0;
    }

    public static int getFreeDelivererRandomly(DelivererMapper delivererMapper){
        Deliverer filterDeliver=new Deliverer();
        filterDeliver.setStatusOn("N");
        ArrayList<Deliverer> delivererArrayList= delivererMapper.selectByCase(filterDeliver);
        if (delivererArrayList.size()>0){
            int random=(int)Math.random()*delivererArrayList.size();
            Deliverer tmpDeliverer = delivererArrayList.get(random);
            tmpDeliverer.setStatusOn("Y");
            delivererMapper.updateByPrimaryKeySelective(tmpDeliverer);
            return tmpDeliverer.getUserId();
        }else return -1;

    }

    public static int getOrderForDeliverer(int delivererId){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        OrderMapper orderMapper= sqlSession.getMapper(OrderMapper.class);
        DelivererMapper delivererMapper= sqlSession.getMapper(DelivererMapper.class);

        Order filterOrder=new Order();
        ArrayList<Order> orderArrayList=null;
        if (orderArrayList.size()==0){
            Deliverer tmpDeliverer=delivererMapper.selectByPrimaryKey(delivererId);
            tmpDeliverer.setStatusOn("N");
            delivererMapper.updateByPrimaryKeySelective(tmpDeliverer);
            sqlSession.commit();
            sqlSession.close();
            return 1;
        }else {
            Order tmpOrder=orderArrayList.get(0);
            tmpOrder.setDeliveryUserId(delivererId);
            orderMapper.updateByPrimaryKeySelective(tmpOrder);
            sqlSession.commit();
            sqlSession.close();
            return 0;
        }

    }
}
