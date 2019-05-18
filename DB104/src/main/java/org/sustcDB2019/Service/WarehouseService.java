package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.WarehouseMapper;
import org.sustcDB2019.entity.Warehouse;

import java.util.ArrayList;

public class WarehouseService {
    public static ArrayList<Warehouse> getAllWarehouse(){
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        WarehouseMapper mapper = sqlSession.getMapper(WarehouseMapper.class);
        ArrayList<Warehouse> list=mapper.selectAll();
        sqlSession.close();
        return list;
    }
}
