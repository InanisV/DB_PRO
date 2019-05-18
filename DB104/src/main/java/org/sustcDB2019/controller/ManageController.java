package org.sustcDB2019.controller;

import org.sustcDB2019.entity.*;
import org.sustcDB2019.service.*;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.Scanner;

public class ManageController {
    public static Scanner in = new Scanner(System.in);
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void ManagerView(ManagerService managerService){
        boolean flag = true;
        do {
            System.out.println("Please choose the option:\n" +
                    "1. View personal information\n" +
                    "2. Add new accounts\n" +
                    "3. Check good sales \n" +
                    "4. Check good profits\n" +
                    "5. Check capacity\n" +
                    "6. Manipulate and check goods\n" +
                    "7. Log out");
            // 7 返回goodsin list
            int option = in.nextInt();
            switch (option){
                case 1:
                    System.out.println("Your Personal Information：");
                    System.out.println("Username: " + ManagerService.manager.getUserName());
                    System.out.println("Phone number: " + ManagerService.manager.getPhoneNumber());
                    System.out.println("Warehouse id: " + ManagerService.manager.getWarehouseWarehouseId());
                    modify(ManagerService.manager);
                case 2:
                    addAccount();
                    break;
                case 3:
                    int normal=0;
                    int cool=0;
                    // 调用查看容量方法
                    System.out.println("Remaining capacity of the regular warehouse is: " + normal);
                    System.out.println("Remaining capacity of the frozen warehouse is: " + cool);
                    break;
                case 4:
                    System.out.println();
                    int page = 1;
                    boolean flag3 = true;
                    do {
                        ArrayList<Goods> goods = new ArrayList<Goods>(); // 查看销量
                        showGoods(goods);
                        System.out.println("Please choose the option:\n" +
                                "1. Next page\n" +
                                "2. Jump to page\n" +
                                "3. Return");
                        int option3 = in.nextInt();
                        switch (option3){
                            case 1:
                                page += 1;
                                break;
                            case 2:
                                System.out.print("Please input the page number: ");
                                page = in.nextInt();
                                showGoods(goods);
                                break;
                            case 3:
                                flag3 = false;
                                break;
                            default:
                                System.out.println("Wrong input. Please input again.");
                        }
                    } while (flag3);
                    break;
                case 5:
                    break;
                case 6:
                    boolean flag2 = true;
                    int page2 = 1;
                    do {
                        System.out.println("Please choose the option:\n" +
                                "1. Next page\n" +
                                "2. Jump to page\n" +
                                "3. Search goods\n" +
                                "4. Replenish goods\n" +
                                "5. Check discount\n" +
                                "6. Add or change discount\n" +
                                "7. Check coming expired goods\n" +
                                "8. Return");
                        int option2 = in.nextInt();
                        switch (option2){
                            case 1:
                                page2 += 1;
                                // 查看下一页
                                break;
                            case 2:
                                System.out.print("Please input the page number: ");
                                page2 = in.nextInt();
                                // 查看
                            case 3:
                                System.out.print("Please input the name of the goods: ");
                                String name = in.next();
                                break;
                            case 4:
                                boolean flag4 = true;
                                do {
                                    System.out.println("Please choose the option:\n" +
                                            "1. Replenish new goods\n" +
                                            "2. Replenish existed goods\n" +
                                            "3. Return");
                                    int option3 = in.nextInt();
                                    switch (option3){
                                        case 1:
                                            Goods g = new Goods();
                                            System.out.print("Please input the good name: ");
                                            g.setName(in.next());
//                                            g.setPrice();
                                            System.out.println("Replenish successfully.");
                                            break;
                                        case 2:
                                            System.out.print("Please input the good id: ");
                                            int id = in.nextInt();
                                            System.out.print("Please input the amount: ");
                                            double amount = in.nextDouble();
                                            // 进货
                                            System.out.println("Replenish successfully.");
                                    }
                                } while (flag4);
                                break;
                            case 5:
                                // 打折商品
                                break;
                            case 6:
                                System.out.print("Please input the goods id to make discount: ");
                                int id2 = in.nextInt();
                                System.out.print("Please input the discount: ");
                                double discount = in.nextDouble();
                                // pass id and discount to make discount
                                System.out.println("Discount successfully.");
                                break;
                            case 7:
                                // 过期商品
                                break;
                            case 8:
                                flag2 = false;
                                break;
                            default:
                                System.out.println("Wrong input. Please input again.");
                        }
                    } while (flag2);
                    break;
                case 7:
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong input. Please input again.");
            }
        } while(flag);
    }

    public static void addAccount(){
        System.out.println("Please choose the identity you want to add:\n1. Manager\n2. Deliverer");
        int identity = in.nextInt();
        System.out.print("Please input the username: ");
        String name = in.next();
        System.out.print("Please input the password: ");
        String password = in.next();
        System.out.print("Please input the phone number: ");
        String phone = in.next();
        System.out.print("Please input the warehouse Id: ");
        int id = in.nextInt();
        // 传入参数
        System.out.println("Add new account successfully.");
    }

    public static void modify(Manager manager){
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
                            "4. Warehouse id\n" +
                            "5. Return");
                    int option2 = in.nextInt();
                    switch (option2){
                        case 1:
                            System.out.print("Please input your new username: ");
                            manager.setUserName(in.next());
                            break;
                        case 2:
                            System.out.print("Please input your old password: ");
                            if(in.next().equals(manager.getPassword())){
                                System.out.print("Please input your new password: ");
                                manager.setPassword(in.next());
                            } else {
                                System.out.println("Your input password is wrong.");
                            }
                            break;
                        case 3:
                            System.out.print("Please input your new phone number: ");
                            manager.setPhoneNumber(in.next());
                            break;
                        case 4:
                            System.out.print("Please input your new warehouse id: ");
                            manager.setWarehouseWarehouseId(in.nextInt());
                            break;
                        case 5:
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

    public static void showGoods(ArrayList<Goods> goods){

    }

}
