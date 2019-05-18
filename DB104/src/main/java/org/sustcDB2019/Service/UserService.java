package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.DaoManager;
import org.sustcDB2019.dao.UserMapper;
import org.sustcDB2019.entity.User;

public class UserService {
    User currentUser;


    /*
    return:
    -1 input empty
    -2 username has already exist in db
     */
    public int signUp(String userName,String password,String phoneNumber,String address) {//password need to be hashed
        if (userName.equals("")||password.equals("")||phoneNumber.equals("")||address.equals(""))// one or more of the inputs are empty (or null)
            return -1;
        currentUser=new User(/*userName,password,phoneNumber*/);
//        SqlSession sqlSession=DaoManager

        return 0;
    }

    public int userNameExist(String userName) {
        SqlSession sqlSession= DaoManager.sqlSessionFactory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
//        if (mapper.findUser(userName)==null){//[add mapper] in: userName out: if(userName exist)User obj if(not exist) null
//            return 0;
//        }
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
        //select from the table and receive the User obj
        if (flag){
            currentUser=user;
            return user.getId()/1000000;
        }else {
            return -1;
        }
    }




}
