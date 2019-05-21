package org.sustcDB2019.dao;

import org.apache.ibatis.annotations.Select;
import org.sustcDB2019.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    //tested
    //@Select("select * from user where user_name=#{userName, jdbcType=VARCHAR}")
    User selectByName(String userName);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}


//discount check and update