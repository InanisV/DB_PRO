package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.DelivererMapper;
import org.sustcDB2019.dao.OrderMapper;
import org.sustcDB2019.entity.Deliverer;
import org.sustcDB2019.entity.Order;

import java.util.ArrayList;

public class DelivererService extends UserService {
    Deliverer deliverer= (Deliverer) super.user;

    public int setStatus(String status) {
        deliverer.setStatusOn(status);
        return 0;
    }

    public ArrayList<Order> getCurrentOrder(){
        SqlSession session=DAOService.sqlSessionFactory.openSession();
        OrderMapper mapper=session.getMapper(OrderMapper.class);
        ArrayList<Order> list=mapper.selectByCase();//[add mapper]
        session.close();
        return list;
    }

    public int updateDeliverer(Deliverer deliverer){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        DelivererMapper mapper=sqlSession.getMapper(DelivererMapper.class);
        mapper.updateByPrimaryKeySelective(deliverer);
        return 0;
    }
}
