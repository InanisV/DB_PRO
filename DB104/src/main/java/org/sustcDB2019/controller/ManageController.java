package org.sustcDB2019.controller;

import org.sustcDB2019.entity.Deliverer;
import org.sustcDB2019.entity.Manager;
import org.sustcDB2019.entity.User;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.Scanner;

public class ManageController {
    public static Scanner in = new Scanner(System.in);
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void ManagerView(User user){
        Manager manager = new Manager();  // Load the manager's personal information
        boolean flag = true;
        do {
            System.out.println("Please choose the option:\n" +
                    "1. View personal information\n" +
                    "2. Add new account\n" +
                    "3. View goods information\n" +
                    "4. Log out");
            int option = in.nextInt();
            switch (option){
                case 1:
                    System.out.println("Information"); // Show the manager's personal information
                    MainController.modify(manager);
                case 2:
                    addAccount();
                    break;
                case 3:
                    // Display goods information
                    boolean flag2 = true;
                    do {
                        System.out.println("Please choose the option:\n" +
                                "1. Replenish goods\n" +
                                "2. Add discount\n" +
                                "3. Return");
                        int option2 = in.nextInt();
                        switch (option2){
                            case 1:
                                System.out.print("Please input the goods id to replenish: ");
                                int id = in.nextInt();
                                // pass id to replenish goods
                                System.out.println("Replenish successfully.");
                                break;
                            case 2:
                                System.out.print("Please input the goods id to make discount: ");
                                int id2 = in.nextInt();
                                System.out.print("Please input the discount: ");
                                double discount = in.nextDouble();
                                // pass id and discount to make discount
                                System.out.println("Discount successfully.");
                                break;
                            case 3:
                                flag2 = false;
                            default:
                                System.out.println("Wrong input. Please input again.");
                        }
                    } while (flag2);
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong input. Please input again.");
            }
        } while(flag);
    }

    public static void addAccount(){
        System.out.println("Please choose the identity you want to sign in:\n1. User\n2. Manager\n3. Deliverer");
        int identity = in.nextInt();
        System.out.print("Please input your username: ");
        String name = in.next();
        System.out.print("Please input your password: ");
        String password = in.next();
        System.out.print("Please input your phone number: ");
        String phone = in.next();
        if(identity==1){
            User user = new User();
            user.setUserName(name);
            user.setPassword(password);
            user.setPhoneNumber(phone);
            // Add user information to the database
        } else if(identity==2){
            Manager manager = new Manager();
            manager.setUserName(name);
            manager.setPassword(password);
            manager.setPhoneNumber(phone);
            System.out.print("Please input your warehouse Id: ");
            manager.setWarehouseWarehouseId(in.nextInt());
            // Add user information to the database
        } else if(identity==3){
            Deliverer deliverer = new Deliverer();
            deliverer.setUserName(name);
            deliverer.setPassword(password);
            deliverer.setPhoneNumber(phone);
            System.out.print("Please input your warehouse Id: ");
            deliverer.setWarehouseWarehouseId(in.nextInt());
            deliverer.setStatusOn("Free");
            // Add user information to the database
        }
    }


}
