package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;

public class CasherService {
    public int updateCasher(Casher casher){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        CasherMapper mapper=sqlSession.getMapper(CasherMapper.class);//[add mapper] add casher's entity and mapper [Aready]
        mapper.updateByPrimaryKeySelective(casher);
        return 0;
    }
}
