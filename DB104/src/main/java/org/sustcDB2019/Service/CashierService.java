package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.CashierMapper;
import org.sustcDB2019.entity.Cashier;
import org.sustcDB2019.entity.Sales;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CashierService {
    public int updateCasher(Cashier cashier){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        CashierMapper mapper=sqlSession.getMapper(CashierMapper.class);//[add mapper] add casher's entity and mapper
        mapper.updateByPrimaryKeySelective(cashier);
        return 0;
    }

    public BigDecimal buyOffline(ArrayList<Sales> list){//not completed
        BigDecimal totalPrice=new BigDecimal(0);
        return totalPrice;
    }
}
