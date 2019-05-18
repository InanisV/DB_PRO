package org.sustcDB2019.Service;

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
        //insert to the table here

        return 0;
    }

    public int userNameExist(String userName) {
        if (findUser(userName)==null){//[add mapper] in: userName out: if(userName exist)User obj if(not exist) null
            return 0;
        }
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
