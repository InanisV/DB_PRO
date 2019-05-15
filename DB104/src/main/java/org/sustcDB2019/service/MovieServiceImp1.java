package com.sustcDB2019.DB104.service;

import com.sustcDB2019.DB104.dao.MovieMapper;
import com.sustcDB2019.DB104.entity.Movie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("MovieService")
public class MovieServiceImp1 implements MovieService {
    @Resource
    private MovieMapper movieDao;

    @Override
    public Movie getMovById(long movId) {
        return movieDao.selectByPrimaryKey(movId);
    }


}