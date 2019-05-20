package org.sustcDB2019.controller;

import java.sql.SQLOutput;
import java.util.Scanner;

import org.sustcDB2019.entity.*;
import org.sustcDB2019.service.*;

import javax.crypto.Mac;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainController {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
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
                            ManagerService managerService = (ManagerService) userService;
                            System.out.println("Log in successfully!");
                            ManageController.ManagerView(userService.user.getId());
                            break;
                        case 4:
                            CustomerService customerService = new CustomerService();
                            boolean flag = true;
                            do {
                                System.out.println("Please choose the option:\n" +
                                        "1. Add goods\n" +
                                        "2. Delete goods\n" +
                                        "3. Buy now");
                                int option2 = in.nextInt();
                                switch (option2){
                                    case 1:
                                        System.out.print("Please input the goods id: ");
                                        int id = in.nextInt();
                                        System.out.print("Please input the amount: ");
                                        int amount = in.nextInt();
                                        customerService.addToCart(id, amount);
                                        break;
                                    case 2:
                                        System.out.print("Please input the goods id to delete: ");
                                        int id2 = in.nextInt();
                                        // pass in id to remove items from cart
                                        break;
                                    case 3:
                                        // 购买
                                        flag = false;
                                        break;
                                }
                            } while (flag);
                            break;
                        case 6:
                            System.out.println("Log in successfully!");
                            DelivererService delivererService = (DelivererService) userService;
                            DelivererController.DelivererView(delivererService);
                            break;
                        case 30:
                            System.out.println("Log in successfully!");
                            CustomerService customerService2 = (CustomerService) userService;
                            AdminController.CustomerView(customerService2);
                            break;
                        case -1:
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
