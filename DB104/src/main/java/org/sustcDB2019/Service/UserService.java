package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.CustomerMapper;
import org.sustcDB2019.dao.DaoManager;
import org.sustcDB2019.dao.UserMapper;
import org.sustcDB2019.entity.Customer;
import org.sustcDB2019.entity.User;

import java.math.BigDecimal;

public class UserService {
    User user;


    /*
    return:
    -1 input empty
    -2 username has already exist in db
     */
    public int signUp(String userName,String password,String phoneNumber,String address) {//password need to be hashed
        if (userName.equals("")||password.equals("")||phoneNumber.equals("")||address.equals(""))// one or more of the inputs are empty (or null)
            return -1;
        user=new User(/*userName,password,phoneNumber*/);
        user.setPassword(String.format("%d",password.hashCode()));
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        user.setId(mapper.getMaxCustomerId()+1);//[add mapper] select the max id of customers , return integer only
        mapper.insert(user);
        Customer customer=(Customer) user;
        customer.setAddress(address);
        customer.setCustomerLati(new BigDecimal(Math.random()*0.164798+22.521605));
        customer.setCustomerLong(new BigDecimal(Math.random()*0.42234+113.849056));
        CustomerMapper customerMapper=sqlSession.getMapper(CustomerMapper.class);
        customerMapper.insert(customer);
        return 0;
    }



    public int userNameExist(String userName) {
        SqlSession sqlSession= DAOService.sqlSessionFactory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
//        if (mapper.findUser(userName)==null){//[add mapper] in: userName out: if(userName exist)User obj if(not exist) null
//            return 0;
//        }
        if (mapper.selectByName(userName)==null){//[add mapper] in: userName out: if(userName exist)User obj if(not exist) null
            return 0;
        }
        sqlSession.close();
        return 1;
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
        if (String.format("%d",password.hashCode()).equals(user.getPassword())){
            flag=true;
        }
        if (flag){
            this.user=user;
            return user.getId()/1000000;
        }else {
            return -1;
        }
    }




}
