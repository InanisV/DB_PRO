package com.sustcDB2019.DB104.service;

import com.sustcDB2019.DB104.dao.CountryMapper;
import com.sustcDB2019.DB104.entity.Country;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("CountryService")
public class CountryServiceImp1 implements CountryService {
    @Resource
    private CountryMapper countryDao;
    @Override
    public Country getCountryById(String countryId) {
        return countryDao.selectByPrimaryKey(countryId);
    }
}
