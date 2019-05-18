package org.sustcDB2019.controller;

import java.sql.SQLOutput;
import java.util.Scanner;

import org.sustcDB2019.entity.Customer;
import org.sustcDB2019.entity.User;
import org.sustcDB2019.Service.*;
import org.sustcDB2019.controller.*;

import javax.crypto.Mac;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainController {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        UserService userService = new UserService();
        System.out.println("Welcome to Newly Retailing Chain Store!\nPlease log in or sign up.");
        boolean flag1 = true;
        do {
            System.out.println("1. Log in\n2. Sign up\n3. Exit");
            int option = in.nextInt();
            int identity;
            int back = 1;
            if(option==1){
                User user = new User();
                boolean flag = true;
//                System.out.println("Please choose your identity:\n1. User\n2. Manager\n3. Deliverer");
                do{
//                    identity = in.nextInt();
                    System.out.print("Username: ");
                    user.setUserName(in.next());
                    System.out.print("Password: ");
                    user.setPassword(in.next());
                    flag = true;  // Call the login method, return true if the account password is correct and add the userid
                    // 返回userid 如果>0 则正确
                    // 传入name 和 password
                    switch (back){
                        case 2:
                            //manager
                            break;
                        case 4:
                            break;
                        case 6:
                            //deliverer
                            break;
                        case 30:
                            // customer
                            break;
                        case -1:
                            System.out.println("Your username or password is wrong, please input again.");
                            flag = false;
                            break;
                    }
                } while(!flag);
                System.out.println("Log in successfully!");
                switch (back){
                    case 30:
                        AdminController.CustomerView(user); // 传入service
                        break;
                    case 2:
                        ManageController.ManagerView(user);
                        break;
                    case 6:
                        DelivererController.DelivererView(user);
                        break;
                }
            } else if(option==2){
                Customer customer = new Customer();
                int repite = 0;
                String name;
                do {
                    System.out.print("Please set your username: ");
                    customer.setUserName(in.next());
                    if (repite==1){
                        System.out.println("The username repeated, please input again.");
                    }
                } while (repite==1);
                System.out.print("Please set your password: ");
                customer.setPassword(in.next());
                System.out.print("Please set your phone number: ");
                customer.setPhoneNumber(in.next());
                System.out.print("Please set your address: ");
                customer.setAddress(in.next());
                // 传入字符串
                System.out.println("Sign up successfully.");
            } else if(option==3){
                flag1 = false;
            } else {
                System.out.println("Your input is wrong, please input again.");
            }
        } while (flag1);
        System.out.println("Thank you for your visit. Welcome to come again.");
    }

    public static void modify(User user){
        boolean flag = true;
        do{
            System.out.println("Please choose the option:\n" +
                    "1. Modify personal information\n" +
                    "2. Return");
            if(in.nextInt()==1){
                boolean flag3 = true;
                do {
                    System.out.println("Please choose the information you want to change:\n" +
                            "1. Username\n" +
                            "2. Password\n" +
                            "3. Phone number\n" +
                            "4. Return");
                    int option2 = in.nextInt();
                    switch (option2){
                        case 1:
                            System.out.print("Please input your new username: ");
                            user.setUserName(in.next());
                            break;
                        case 2:
                            System.out.print("Please input your old password: ");
                            if(in.next().equals(user.getPassword())){
                                System.out.print("Please input your new password: ");
                                user.setPassword(in.next());
                            } else {
                                System.out.println("Your input password is wrong.");
                            }
                            break;
                        case 3:
                            System.out.print("Please input your new phone number: ");
                            user.setPhoneNumber(in.next());
                            break;
                        case 4:
                            // Update personal information to database
                            // UpdateManager
                            // 返回 0 正常  其他则update失败
                            System.out.println("Modify successfully.");
                            flag3 = false;
                        default:
                            System.out.println("Your input is wrong, please input again.");
                    }
                } while (flag3);
            } else {
                flag = false;
            }
        } while (flag);
    }


}
