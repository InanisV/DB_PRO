package org.sustcDB2019.controller;

import org.sustcDB2019.entity.Deliverer;
import org.sustcDB2019.entity.Order;
import org.sustcDB2019.entity.User;
import org.sustcDB2019.service.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DelivererController {
    public static Scanner in = new Scanner(System.in);
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void DelivererView(int delivererid){
        DelivererService delivererService = new DelivererService();
        ManagerService managerService = new ManagerService();
        delivererService.deliverer = managerService.getDelivererById(delivererid);
        boolean flag = true;
        do {
            System.out.println("Please choose the option:\n" +
                    "1. View personal information\n" +
                    "2. View order tasks\n" +
                    "3. Log out");
            int option = in.nextInt();
            switch (option){
                case 1:
                    System.out.println("Your Personal Information：");
                    System.out.println("Username: " + delivererService.deliverer.getUserName());
                    System.out.println("Phone number: " + delivererService.deliverer.getPhoneNumber());
                    System.out.println("Warehouse id: " + delivererService.deliverer.getWarehouseWarehouseId());
                    modify(delivererService.deliverer);
                    break;
                case 2:
                    ArrayList<Order> order = delivererService.getCurrentOrder();
                    showOrders(order, delivererService);
                    boolean flag2 = true;
                    do {
                        System.out.println("Please choose the option:\n" +
                                "1. Accept order\n" +
                                "2. Return");
                        int option2 = in.nextInt();
                        switch (option2){
                            case 1:
                                if(delivererService.orderDepart(new Date())==1){
                                    System.out.println("No order accepted.");
                                } else {
                                    System.out.println("Accept successfully.");
                                }
                                break;
                            case 2:
                                flag2 = false;
                                break;
                            default:
                                System.out.println("Wrong input.");
                                break;
                        }
                    } while (flag2);
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong input. Please input again.");
                    break;
            }
        } while(flag);
    }

    public static void modify(Deliverer deliverer){
        DelivererService delivererService = new DelivererService();
        CustomerService customerService = new CustomerService();
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
                            int repite = 0;
                            do {
                                System.out.print("Please set your username: ");
                                String name = in.next();
                                repite = customerService.userNameExist(name);
                                if (repite==1){
                                    System.out.println("The username repeated, please input again.");
                                } else {
                                    delivererService.deliverer.setUserName(name);
                                }
                            } while (repite==1);
                            break;
                        case 2:
                            System.out.print("Please input your old password: ");
                            if(in.next().equals(deliverer.getPassword())){
                                System.out.print("Please input your new password: ");
                                deliverer.setPassword(in.next());
                            } else {
                                System.out.println("Your input password is wrong.");
                            }
                            break;
                        case 3:
                            System.out.print("Please input your new phone number: ");
                            deliverer.setPhoneNumber(in.next());
                            break;
                        case 4:
                            if(delivererService.updateDeliverer(deliverer)==0){
                                System.out.println("Modify successfully.");
                            } else {
                                System.out.println("Modification fails");
                            }
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

    public static void showOrders(ArrayList<Order> orders, DelivererService delivererService){
        System.out.println(String.format("%-16s%-28s%-17s%-17s%-20s", "Order id", "Departure time",
                "Customer name", "Phone number", "Address"));
        for (Order x : orders) {
            ManagerService managerService = new ManagerService();
            System.out.println(String.format(String.format("%-16s%-28s%-17s%-17s%-20s", x.getOrderId(), x.getDepartureTime(),
                    managerService.getCustomerById(x.getCustomerUserId()).getUserName(),
                    managerService.getCustomerById(x.getCustomerUserId()).getPhoneNumber(),
                    managerService.getCustomerById(x.getCustomerUserId()).getAddress())));
        }
    }

}
