package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.*;
import org.sustcDB2019.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class ManagerService extends UserService{
    public Manager manager=new Manager();


    public int addNewManager(String userName,String password,String phoneNumber,int warehouseId){
        Manager newManager=new Manager();
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectByName(userName);
        if(user!=null){
            sqlSession.commit();
            sqlSession.close();
            return -1;
        }else {
            user = new User();
        }
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        user.setId(managerMapper.selectMaxId()+1);
        user.setPassword(String.format("%d",password.hashCode()));
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        newManager.setUserId(user.getId());
        newManager.setWarehouseWarehouseId(warehouseId);
        userMapper.insertSelective(user);
        sqlSession.commit();

        managerMapper.insertSelective(newManager);
        user = userMapper.selectByName(userName);
        sqlSession.commit();
            sqlSession.close();
        if(user==null){
            return -1;
        }
        return 0;
    }

    public int updateManager(Manager manager){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        ManagerMapper mapper=sqlSession.getMapper(ManagerMapper.class);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        User tmpUser=new User(manager);

        if (tmpUser.getPassword()!=null&&tmpUser.getUserName()!=null&&tmpUser.getPhoneNumber()!=null){
            userMapper.updateByPrimaryKeySelective(tmpUser);
        }

        if (manager.getWarehouseWarehouseId()!=null){
            mapper.updateByPrimaryKeySelective(manager);
        }

        sqlSession.commit();
            sqlSession.close();
        return 0;
    }

    public Goods addNewGoods(Goods newGoods){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        GoodsMapper goodsMapper=sqlSession.getMapper(GoodsMapper.class);
        goodsMapper.insertSelective(newGoods);
        sqlSession.commit();
        Goods tmpGoods=goodsMapper.selectConditionally(newGoods).get(0);
        sqlSession.close();
        return tmpGoods;
    }

    public int changeGoodsDiscount(int goodsId, double discount){
        BigDecimal discountBD=new BigDecimal(discount);
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        Goods tmpGoods=new Goods();
        tmpGoods.setGoodsId(goodsId);
        tmpGoods.setDiscount(discountBD);
        goodsMapper.updateByPrimaryKeySelective(tmpGoods);
        sqlSession.commit();
            sqlSession.close();
        return 0;
    }


    public int purchaseToWarehouse( int goodsId, int amount, Date productionDate){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        GoodsMapper goodsMapper=sqlSession.getMapper(GoodsMapper.class);
        WarehouseMapper warehouseMapper=sqlSession.getMapper(WarehouseMapper.class);
        if (goodsMapper.selectByPrimaryKey(goodsId).getRefrigiratedCondition().equals("Y")){
            if (amount>warehouseMapper.getRefriRestVolume(manager.getWarehouseWarehouseId())) {
                sqlSession.commit();
            sqlSession.close();
                return 1;
            }
        }else {
            if (amount>warehouseMapper.getNonRefriRestVolume(manager.getWarehouseWarehouseId())) {
                sqlSession.commit();
            sqlSession.close();
                return 1;
            }
        }
        Purchase purchase=new Purchase();
        purchase.setGoodsGoodsId(goodsId);
        purchase.setAmount(amount);
        purchase.setProductionDate(productionDate);
        purchase.setWarehouseWarehouseId(manager.getWarehouseWarehouseId());
        purchase.setDate(currentDate);
        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        Goods goods=mapper.selectByPrimaryKey(goodsId);
        purchase.setCost(goods.getPrice().multiply(new BigDecimal(amount)));
        PurchaseMapper mapper1=sqlSession.getMapper(PurchaseMapper.class);
        mapper1.insertSelective(purchase);
        sqlSession.commit();
        sqlSession.close();
        return 0;
    }

    public User getUserByName(String name) {
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user =mapper.selectByName(name);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }

    public Manager getManagerById(int id){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        ManagerMapper mapper=sqlSession.getMapper(ManagerMapper.class);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        Manager tmpManager =mapper.selectByPrimaryKey(id);
        tmpManager.setByUser(userMapper.selectByPrimaryKey(id));
        sqlSession.commit();
            sqlSession.close();
        return tmpManager;
    }

    public Customer getCustomerById(int id){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        CustomerMapper mapper=sqlSession.getMapper(CustomerMapper.class);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        Customer tmpCustomer =mapper.selectByPrimaryKey(id);
        tmpCustomer.setByUser(userMapper.selectByPrimaryKey(id));
        sqlSession.commit();
            sqlSession.close();
        return tmpCustomer;
    }

    public Deliverer getDelivererById(int id){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        DelivererMapper mapper=sqlSession.getMapper(DelivererMapper.class);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        Deliverer tmpDeliverer =mapper.selectByPrimaryKey(id);
        tmpDeliverer.setByUser(userMapper.selectByPrimaryKey(id));
        sqlSession.commit();
            sqlSession.close();
        return tmpDeliverer;
    }

    public Cashier getCashierById(int id){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        CashierMapper mapper=sqlSession.getMapper(CashierMapper.class);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        Cashier tmpCashier =mapper.selectByPrimaryKey(id);
        tmpCashier.setByUser(userMapper.selectByPrimaryKey(id));
        sqlSession.commit();
            sqlSession.close();
        return tmpCashier;
    }


    public int[] getRestVolume(int warehouseId){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        WarehouseMapper mapper=sqlSession.getMapper(WarehouseMapper.class);

        int [] volumes=null;
        volumes[1]=mapper.getRefriRestVolume(manager.getWarehouseWarehouseId());
        volumes[0]=mapper.getNonRefriRestVolume(manager.getWarehouseWarehouseId());
        //[add mapper]
        //搞定了  、、@fixed: the four new methods was added
        sqlSession.commit();
            sqlSession.close();
        return volumes;
    }
    public ArrayList<GoodsInWarehouse> getNearOverdue(){//filter type? category? RefrigiratedCondition? or goods obj?
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        GoodsInWarehouseMapper goodsInWarehouseMapper=sqlSession.getMapper(GoodsInWarehouseMapper.class);
        ArrayList<GoodsInWarehouse> list=null;
//        list=goodsInWarehouseMapper.nearlyExpired(manager.getWarehouseWarehouseId());
        // select GoodsInWarehouse whose remaining time = 10% * preserveTime
        sqlSession.commit();
            sqlSession.close();
        return list;
    }


    public ArrayList<GoodsWithAmountIncome> getOrderedBySalesVolume(int pageIndex){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        SalesMapper salesMapper=sqlSession.getMapper(SalesMapper.class);
        ArrayList<GoodsWithAmountIncome> list=salesMapper.getSalesVolumeRank(manager.getWarehouseWarehouseId(),20,pageIndex*20);
        sqlSession.commit();
            sqlSession.close();
        return list;
    }

    public ArrayList<GoodsWithAmountIncome> getOrderedByIncome(int pageIndex){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        SalesMapper salesMapper=sqlSession.getMapper(SalesMapper.class);
        ArrayList<GoodsWithAmountIncome> list=salesMapper.getSalesIncomeRank(manager.getWarehouseWarehouseId(),20,pageIndex);
        sqlSession.commit();
            sqlSession.close();
        return list;
    }

    //return id of new warehouse
    public int addNewWarehouse(String address, int refrigeratedShelfVolume, int ordinaryShelfVolume, BigDecimal warehouseLong,BigDecimal warehouseLati){//BigDecimal or Long or Integer?
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        WarehouseMapper warehouseMapper = sqlSession.getMapper(WarehouseMapper.class);
        Warehouse tmpWarehouse=new Warehouse();
        tmpWarehouse.setAddress(address);
        tmpWarehouse.setRefrigeratedShelfVolume(refrigeratedShelfVolume);
        tmpWarehouse.setOrdinaryShelfVolume(ordinaryShelfVolume);
        tmpWarehouse.setWarehouseLati(warehouseLati);
        tmpWarehouse.setWarehouseLong(warehouseLong);
        warehouseMapper.insertSelective(tmpWarehouse);
        int maxId= warehouseMapper.selectMaxId();
        sqlSession.commit();
            sqlSession.close();
        return maxId;
    }

    public int addNewCashier(String userName,String password,String phoneNumber,int warehouseId){
        Cashier newCashier=new Cashier();
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectByName(userName);
        if(user!=null){
            sqlSession.commit();
            sqlSession.close();
            return -1;
        }else {
            user = new User();
        }
        CashierMapper cashierMapper = sqlSession.getMapper(CashierMapper.class);
        user.setId(cashierMapper.selectMaxId()+1);
        user.setPassword(String.format("%d",password.hashCode()));
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        newCashier.setUserId(user.getId());
        newCashier.setWarehouseWarehouseId(warehouseId);
        userMapper.insertSelective(user);
        sqlSession.commit();

        cashierMapper.insertSelective(newCashier);
        user = userMapper.selectByName(userName);
        sqlSession.commit();
        sqlSession.close();
        if(user==null){
            return -1;
        }
        return 0;
    }

    public int addNewDeliverer(String userName,String password,String phoneNumber,int warehouseId){
        Deliverer newDeliverer=new Deliverer();
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectByName(userName);
        if(user!=null){
            sqlSession.commit();
            sqlSession.close();
            return -1;
        }else {
            user = new User();
        }
        DelivererMapper delivererMapper = sqlSession.getMapper(DelivererMapper.class);
        user.setId(delivererMapper.selectMaxId()+1);
        user.setPassword(String.format("%d",password.hashCode()));
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        newDeliverer.setUserId(user.getId());
        newDeliverer.setWarehouseWarehouseId(warehouseId);
        newDeliverer.setStatusOn("N");
        userMapper.insertSelective(user);
        sqlSession.commit();

        delivererMapper.insertSelective(newDeliverer);
        user = userMapper.selectByName(userName);
        sqlSession.commit();
        sqlSession.close();
        if(user==null){
            return -1;
        }
        return 0;
    }
}