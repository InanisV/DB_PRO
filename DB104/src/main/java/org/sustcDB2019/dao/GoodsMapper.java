package org.sustcDB2019.dao;

import org.sustcDB2019.entity.Goods;

import java.util.ArrayList;

public interface GoodsMapper {

    //need to be test
    ArrayList<Goods> selectConditionally(Goods goods);


    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}