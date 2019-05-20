package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.*;
import org.sustcDB2019.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Math.*;

public class CustomerService extends UserService {
    public Customer customer = (Customer) super.user;

    public int updateCustomer() {
        return 0;
    }

    public ArrayList<Sales> showCart(Integer userId) {
        SqlSession session = DAOService.sqlSessionFactory.openSession();
        SalesMapper salesMapper = session.getMapper(SalesMapper.class);
        Sales sale = new Sales();
        sale.setCustomerUserId(userId);
        sale.setIsPaid("N");
        ArrayList<Sales> result = salesMapper.selectByCase(sale);
        session.close();
        return result;
    }

    public void updateWarehouse() {//start when logitude and latitude are changed or user is just sign in
        ArrayList<Warehouse> warehouses = WarehouseService.getAllWarehouse();
        double R = 6371.0;
        int minWarehouseId = 0;
        double minDistance = 0;
        for (int i = 0; i < warehouses.size(); i++) {
            double phi1 = (90 - customer.getCustomerLati().doubleValue()) * PI / 180, phi2 = (90 - warehouses.get(i).getWarehouseLati().doubleValue()) * PI / 180;
            double c = sin(phi1) * sin(phi2) * cos((customer.getCustomerLong().doubleValue() - warehouses.get(i).getWarehouseLong().doubleValue()) * PI / 180) + cos(phi1) *
                    cos(phi2);
            double d = R * acos(c);
            if (d < minDistance) {
                minDistance = d;
                minWarehouseId = i;
            }
        }
        customer.setWarehouseId(minWarehouseId);
    }

//
//    public ArrayList<Goods> goodsArrayList() {
//
//    }

    public ArrayList<Goods> goodsArrayListWithFilter(Goods filterGoods, int warehouseId, String lowerPerice, String upperPirce, boolean discount, String orderByPriceIncrease, boolean orderByDiscount, int index) {//
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        GoodsInWarehouseMapper goodsInWarehouseMapper = sqlSession.getMapper(GoodsInWarehouseMapper.class);
        ArrayList<Goods> list = goodsInWarehouseMapper.selectConditionallyWithPages(
                customer.getWarehouseId() > 0 ? String.format("%d", customer.getWarehouseId()) : null, filterGoods.getType(),
                filterGoods.getCatagory(),
                filterGoods.getName(),
                filterGoods.getBrand(),
                filterGoods.getOriginPlace(),
                filterGoods.getRefrigiratedCondition(),
                lowerPerice, upperPirce, discount, orderByPriceIncrease, orderByDiscount, 10, index);
        sqlSession.close();
        return list;
    }

    public int addToCart(int goodsId, int amount) {
//        Sales sales=new Sales();
//        sales.setAmount(amount);
//        sales.setGoodsInWarehouseId(goodsId);
//        sales.setIsPaid("N");
//        sales.setCustomerUserId(customer.getId());
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
//        BigDecimal amountDecimal=new BigDecimal(amount);
        GoodsInWarehouseMapper goodsInWarehouseMapper = sqlSession.getMapper(GoodsInWarehouseMapper.class);
        ArrayList<GoodsInWarehouse> list = goodsInWarehouseMapper.selectByCase(goodsId, customer.getWarehouseId());
        int rest = 0;
        for (GoodsInWarehouse goodsInWarehouse : list) {
            rest += goodsInWarehouse.getAmount();
        }
        if (rest < amount) return 1;// fail to add to cart since amount excceed
        int tmpAmount = amount;
        SalesMapper salesMapper = sqlSession.getMapper(SalesMapper.class);
        for (int i = 0; i < list.size(); i++) {
            Sales tmpSales = new Sales();
            GoodsInWarehouse tmpGIW = list.get(i);
            if (tmpGIW.getAmount() >= tmpAmount) {
                tmpSales.setAmount(tmpAmount);
                tmpSales.setGoodsInWarehouseId(tmpGIW.getIdgoodsInWarehouse());
                tmpSales.setCustomerUserId(customer.getId());
                tmpSales.setIsPaid("N");
                tmpSales.setPayment(goods.getPrice().multiply(goods.getDiscount()).multiply(new BigDecimal(tmpAmount)));
                salesMapper.insertSelective(tmpSales);
                tmpGIW.setAmount(tmpGIW.getAmount() - tmpAmount);
                goodsInWarehouseMapper.updateByPrimaryKeySelective(tmpGIW);
                break;
            } else {
                tmpSales.setAmount(tmpGIW.getAmount());
                tmpSales.setGoodsInWarehouseId(tmpGIW.getIdgoodsInWarehouse());
                tmpSales.setCustomerUserId(customer.getId());
                tmpSales.setIsPaid("N");
                tmpSales.setPayment(goods.getPrice().multiply(goods.getDiscount()).multiply(new BigDecimal(tmpGIW.getAmount())));
                salesMapper.insertSelective(tmpSales);
                tmpGIW.setAmount(0);
                goodsInWarehouseMapper.updateByPrimaryKeySelective(tmpGIW);
            }
        }
        return 0;
    }

    //add return 1: sales not belong to this customer
    public int cancleSales(ArrayList<Sales> list){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        SalesMapper salesMapper=sqlSession.getMapper(SalesMapper.class);
        GoodsInWarehouseMapper goodsInWarehouseMapper=sqlSession.getMapper(GoodsInWarehouseMapper.class);
        for (Sales sales:list) {
            if (sales.getCustomerUserId()!=customer.getUserId())return 1;
        }
        for (Sales sales:list) {
            GoodsInWarehouse tmpGoodsInWarehouse=new GoodsInWarehouse();
            {
                tmpGoodsInWarehouse=goodsInWarehouseMapper.selectByPrimaryKey(sales.getGoodsInWarehouseId());
                tmpGoodsInWarehouse.setAmount(tmpGoodsInWarehouse.getAmount()+sales.getAmount());
                goodsInWarehouseMapper.updateByPrimaryKeySelective(tmpGoodsInWarehouse);
            }// can use a new update to simplefy this block
            salesMapper.deleteByPrimaryKey(sales.getSalesId());
        }
        return 0;
    }

    // buy method need to be fixed
    // return -1 if sales not belong to this customer
    public int buy(ArrayList<Sales> list){
        for (Sales sales:list) {
            if (sales.getCustomerUserId()!=customer.getUserId()) return -1;
        }
        Order tmpOrder=new Order();
        tmpOrder.setSaleses(list);// maybe saleses is not needed
        tmpOrder.setCustomerUserId(customer.getUserId());
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        DelivererMapper delivererMapper = sqlSession.getMapper(DelivererMapper.class);
        SalesMapper salesMapper = sqlSession.getMapper(SalesMapper.class);

        Deliverer tmpDeliverer=null;
        //
        int orderId=0;
        orderId=orderMapper.selectMaxId();
        //[add mapper] select max id of order
        //搞定了
        for (Sales sales:list) {
            sales.setIsPaid("Y");
            sales.setOrderOrderId(orderId);
            salesMapper.updateByPrimaryKeySelective(sales);
        }


        GoodsInWarehouseMapper goodsInWarehouseMapper=sqlSession.getMapper(GoodsInWarehouseMapper.class);
        goodsInWarehouseMapper.deleteAll();
        //[add mapper] updateAll to delete all goodsInWarehouse whose amount==0
        //搞定了
        return tmpOrder.getOrderId();//return id of order for front to view relevant message
    }


    public int updateCustomer(Customer customer) {
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        mapper.updateByPrimaryKeySelective(customer);
        return 0;
    }

    public ArrayList<Order> getOrder() {
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order tmpOrder = new Order();
        tmpOrder.setCustomerUserId(customer.getId());
        ArrayList<Order> list = mapper.selectByCase(tmpOrder);//[add mapper]
        return list;
    }

    public int getHistoryStatistics(Date startDate,Date endDate){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        SalesMapper salesMapper= sqlSession.getMapper(SalesMapper.class);
        return salesMapper.countPaymentByIdAndDate(customer.getId(),startDate,endDate);
    }
    //test
    public ArrayList<Integer> getHistoryStatisticsByMonth(Date startDate,Date endDate){
        SqlSession sqlSession= DAOService.sqlSessionFactory.openSession();
        SalesMapper salesMapper=sqlSession.getMapper(SalesMapper.class);
        ArrayList<Integer> list=new ArrayList<>();
        Calendar startCalendar=Calendar.getInstance();
        Calendar endCalendar=Calendar.getInstance();
        Calendar tmpCalendar=Calendar.getInstance();
        startCalendar.setTime(startDate);
        startCalendar.set(Calendar.DAY_OF_MONTH,startCalendar.getMinimum(Calendar.DAY_OF_MONTH));
        tmpCalendar.setTime(startDate);
        tmpCalendar.set(Calendar.DAY_OF_MONTH,startCalendar.getMinimum(Calendar.DAY_OF_MONTH));
        endCalendar.setTime(endDate);
        endCalendar.set(Calendar.DAY_OF_MONTH,endCalendar.getMaximum(Calendar.DAY_OF_MONTH));
        for (; startCalendar.before(endCalendar); ) {
            list.add(salesMapper.countPaymentByIdAndDate(customer.getId(),new Date(startCalendar.getTimeInMillis()),new Date(tmpCalendar.getTimeInMillis())));
            startCalendar.set(Calendar.MONTH, startCalendar.get(Calendar.MONTH) + 1);
            tmpCalendar.set(Calendar.MONTH, startCalendar.get(Calendar.MONTH) + 1);
        }
        return list;
    }


}
