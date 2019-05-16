package org.sustcDB2019.controller;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.*;
import org.sustcDB2019.entity.Customer;
import org.sustcDB2019.entity.Deliverer;
import org.sustcDB2019.entity.Manager;
import org.sustcDB2019.entity.User;

public class AccountController {
    public static void signIn(User user){
        SqlSession session= DaoManager.sqlSessionFactory.openSession();
        UserMapper mapper =  session.getMapper(UserMapper.class);
        User u = mapper.selectByName(user.getUserName());
        if(u==null){
            //用户不存在
            return;
        }
        if(u.getPassword()!=user.getPassword()){
            //密码错误
            return;
        }

    }

    public static void SignUp(User user, Customer customer){

        //处理,用户名密码是否符合规范

        SqlSession session= DaoManager.sqlSessionFactory.openSession();
        UserMapper mapper =  session.getMapper(UserMapper.class);
        mapper.insert(user);
        CustomerMapper mapper1 = session.getMapper(CustomerMapper.class);
        mapper1.insert(customer);
        session.close();
    }

    public static void SignUp(User user, Manager manager){

        //处理,用户名密码是否符合规范

        SqlSession session= DaoManager.sqlSessionFactory.openSession();
        UserMapper mapper =  session.getMapper(UserMapper.class);
        mapper.insert(user);
        ManagerMapper mapper1 = session.getMapper(ManagerMapper.class);
        mapper1.insert(manager);
        session.close();
    }

    public static void SignUp(User user, Deliverer deliverer){

        //处理,用户名密码是否符合规范

        SqlSession session= DaoManager.sqlSessionFactory.openSession();
        UserMapper mapper =  session.getMapper(UserMapper.class);
        mapper.insert(user);
        DelivererMapper mapper1 = session.getMapper(DelivererMapper.class);
        mapper1.insert(deliverer);
        session.close();
    }

    public void changeInfo(User user, Customer u){
        SqlSession session= DaoManager.sqlSessionFactory.openSession();
        UserMapper mapper =  session.getMapper(UserMapper.class);
        mapper.updateByPrimaryKeySelective(user);
        CustomerMapper mapper1 = session.getMapper(CustomerMapper.class);
        mapper1.updateByPrimaryKeySelective(u);
        session.close();
    }

    public void changeInfo(User user, Manager u){
        SqlSession session= DaoManager.sqlSessionFactory.openSession();
        UserMapper mapper =  session.getMapper(UserMapper.class);
        mapper.updateByPrimaryKeySelective(user);
        ManagerMapper mapper1 = session.getMapper(ManagerMapper.class);
        mapper1.updateByPrimaryKeySelective(u);
        session.close();
    }

    public void changeInfo(User user, Deliverer u){
        SqlSession session= DaoManager.sqlSessionFactory.openSession();
        UserMapper mapper =  session.getMapper(UserMapper.class);
        mapper.updateByPrimaryKeySelective(user);
        DelivererMapper mapper1 = session.getMapper(DelivererMapper.class);
        mapper1.updateByPrimaryKeySelective(u);
        session.close();
    }

}
