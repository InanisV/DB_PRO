package com.sustcDB2019.DB104.dao;

import com.sustcDB2019.DB104.entity.Country;

public interface CountryMapper {
    int deleteByPrimaryKey(String countryCode);

    int insert(Country record);

    int insertSelective(Country record);

    Country selectByPrimaryKey(String countryCode);

    int updateByPrimaryKeySelective(Country record);

    int updateByPrimaryKey(Country record);
}