package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.CustomerMapper;
import org.sustcDB2019.dao.UserMapper;
import org.sustcDB2019.entity.Customer;
import org.sustcDB2019.entity.User;

import java.math.BigDecimal;
import java.util.Date;

public class UserService {
    public User user;
    Date currentDate;


    /*
    return:
    -1 input empty
    -2 username has already exist in db
     */
    public int signUp(String userName,String password,String phoneNumber,String address) {//password need to be hashed
        if (userName.equals("")||password.equals("")||phoneNumber.equals("")||address.equals(""))// one or more of the inputs are empty (or null)
            return -1;
        Customer customer=new Customer();
        user=(User) customer;
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        user.setPassword(String.format("%d",password.hashCode()));
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        CustomerMapper mapper1=sqlSession.getMapper(CustomerMapper.class);
        user.setId(mapper1.selectMaxId()+1);//[add mapper] select the max id of customers , return integer only
        mapper.insertSelective(user);
        sqlSession.commit();
        customer.setAddress(address);
        customer.setId(mapper1.selectMaxId());
        customer.setCustomerLati(new BigDecimal(Math.random()*0.164798+22.521605));
        customer.setCustomerLong(new BigDecimal(Math.random()*0.42234+113.849056));
        CustomerMapper customerMapper=sqlSession.getMapper(CustomerMapper.class);
        customerMapper.insertSelective(customer);
        sqlSession.commit();
            sqlSession.close();
        return 0;
    }



    public int userNameExist(String userName) {
        SqlSession sqlSession= DAOService.sqlSessionFactory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User tmpUser=mapper.selectByName(userName);
        if (tmpUser==null){
            return 0;
        }
        sqlSession.commit();
            sqlSession.close();
        return tmpUser.getId()/1000000;
    }



    /*
    return:
    2 manager sign in successfully
    6 deliverer sign in successfully
     */
    public int signIn(String userName,String password){
        boolean flag=false;
        User user=null;
        SqlSession session=DAOService.sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        user =mapper.selectByName(userName);
        if(user==null) {
            session.close();
            return -1;
        }
//        if (String.format("%d",password.hashCode()).equals(user.getPassword())){
        if (password.equals(user.getPassword())){
            flag=true;
        }
        session.close();
        if (flag){
            this.user=user;
            return user.getId()/1000000;
        }else {
            return -1;
        }
    }




}
