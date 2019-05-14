package com.sustcDB2019.DB104.dao;

import com.sustcDB2019.DB104.entity.Movie;

public interface MovieMapper {
    int deleteByPrimaryKey(Long movieid);

    int insert(Movie record);

    int insertSelective(Movie record);

    Movie selectByPrimaryKey(Long movieid);

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKey(Movie record);
}