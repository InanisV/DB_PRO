package org.sustcDB2019.controller;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.WarehouseMapper;
import org.sustcDB2019.entity.*;
import org.sustcDB2019.service.*;

public class MainController {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
//        DAOService s = new DAOService();
////        Manager manager=new Manager(new User(2000008,"12","asdcd","12312312321"));
//        ManagerService managerService=new ManagerService();
//        Manager manager=managerService.getManagerById(2000085);
//        Calendar calendar=Calendar.getInstance();
//        Date dateee=new Date(calendar.getTimeInMillis());
//        managerService.purchaseToWarehouse(1002,11,dateee);
//        manager.setWarehouseWarehouseId(11);
//        managerService.manager=manager;
//        managerService.updateManager(manager);
//        System.out.println();
//        DAOService s = new DAOService();
//        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
//        WarehouseMapper mapper = sqlSession.getMapper(WarehouseMapper.class);
//        ManagerService managerService=new ManagerService();
//        CustomerService customerService=new CustomerService();
//        customerService.customer=new Customer(managerService.getUserByName("Colemin531"));
//        managerService.manager=new Manager();
//        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.MONTH,4);
//        ArrayList<Integer> list=customerService.getHistoryStatisticsByMonth(new Date(calendar.getTimeInMillis()),new Date(calendar.getTimeInMillis()));



        DAOService s = new DAOService();
        UserService userService = new UserService();
        System.out.println("Welcome to Newly Retailing Chain Store!\nPlease log in or sign up.");
        boolean flag1 = true;
        do {
            System.out.println("1. Log in\n2. Sign up\n3. Exit");
            int option = in.nextInt();
            int back = 1;
            if(option==1){
                do{
                    System.out.print("Username: ");
                    String name = in.next();
                    System.out.print("Password: ");
                    String password = in.next();
                    back = userService.signIn(name, password);
                    switch (back){
                        case 2:
                            System.out.println("Log in successfully!");
                            ManageController.ManagerView(userService.user.getId());
                            break;
                        case 4:
                            System.out.println("Log in successfully!");
                            CashierController.CashierView(userService.user.getId());
                            break;
                        case 6:
                            System.out.println("Log in successfully!");
                            DelivererController.DelivererView(userService.user.getId());
                            break;
                        case 30:
                            System.out.println("Log in successfully!");
                            AdminController.CustomerView(userService.user.getId());
                            break;
                        default:
                            System.out.println("Your username or password is wrong, please input again.");
                            break;
                    }
                } while(back==-1);
            } else if(option==2){
                Customer customer = new Customer();
                int repite = 0;
                do {
                    System.out.print("Please set your username: ");
                    customer.setUserName(in.next());
                    repite = userService.userNameExist(customer.getUserName());
                    if (repite!=0){
                        System.out.println("The username repeated, please input again.");
                    }
                } while (repite!=0);
                System.out.print("Please set your password: ");
                customer.setPassword(in.next());
                System.out.print("Please set your phone number: ");
                customer.setPhoneNumber(in.next());
                System.out.print("Please set your address: ");
                customer.setAddress(in.next());
                userService.signUp(customer.getUserName(), customer.getPassword(), customer.getPhoneNumber(), customer.getAddress());
                System.out.println("Sign up successfully.");
            } else if(option==3){
                flag1 = false;
            } else {
                System.out.println("Your input is wrong, please input again.");
            }
        } while (flag1);
        System.out.println("Thank you for your visit. Welcome to come again.");
    }

}
