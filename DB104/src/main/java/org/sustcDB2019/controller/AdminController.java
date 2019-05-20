package org.sustcDB2019.controller;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.sustcDB2019.entity.*;
import org.sustcDB2019.service.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AdminController {
    public static Scanner in = new Scanner(System.in);
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void CustomerView(CustomerService customerService){
        boolean flag = true;
        do {
            System.out.println("Please choose the option:\n" +
                    "1. View personal information\n" +
                    "2. Purchase goods\n" +
                    "3. View historical orders\n" +
                    "4. Log out");
            int option = in.nextInt();
            switch (option){
                case 1:
                    System.out.println("Your Personal Information：");
                    System.out.println("Username: " + customerService.customer.getUserName());
                    System.out.println("Phone number: " + customerService.customer.getPhoneNumber());
                    System.out.println("Address: " + customerService.customer.getAddress());
                    modify(customerService.customer);
                    break;
                case 2:
                    int page  = 1;
                    ArrayList<Goods> goods = new ArrayList<Goods>();
                    boolean flag2 = true;
                    do {
                        System.out.println("Please choose the option:\n" +
                                "1. Next page\n" +
                                "2. Jump to page\n" +
                                "3. Search goods\n" +
                                "4. Add goods to cart\n" +
                                "5. Check the cart and purchase\n" +
                                "6. Return");
                        int option2 = in.nextInt();
                        switch (option2){
                            case 1:
                                page += 1;
                                // 查看下一页
                                showGoods(goods);
                                break;
                            case 2:
                                System.out.print("Please input the page number: ");
                                page = in.nextInt();
                                showGoods(goods);
                                // 查看
                            case 3:
                                System.out.print("Please input the name of the goods: ");
                                String name = in.next();
                                break;
                            case 4:
                                System.out.print("Please input the goods id: ");
                                int id = in.nextInt();
                                System.out.print("Please input the amount: ");
                                int amount = in.nextInt();
                                // pass in id and quantity to add to the cart
                                break;
                            case 5:
                                // displays shopping cart items and amounts
                                showGoods(goods);
                                double total = 0;
                                boolean flag3 = true;
                                do {
                                    System.out.println("Please choose the option:\n" +
                                            "1. Delete the goods\n" +
                                            "2. Buy now\n" +
                                            "3. Return");
                                    int option3 = in.nextInt();
                                    switch (option3){
                                        case 1:
                                            System.out.print("Please input the goods id to delete: ");
                                            int id2 = in.nextInt();
                                            // pass in id to remove items from cart
                                            break;
                                        case 2:
                                            df.format(new Date()); //Current system time
                                            // 传入顾客id warehouseid 时间
                                            System.out.println("Order generated successfully.");
                                            Order order = new Order();
                                            flag3 = false;
                                            break;
                                        case 3:
                                            flag3 = false;
                                            break;
                                        default:
                                            System.out.println("Wrong input. Please input again.");
                                    }
                                } while (flag3);
                            case 6:
                                flag2 = false;
                            default:
                                System.out.println("Wrong input. Please input again.");
                        }
                    } while (flag2);
                    break;
                case 3:
                    // show historical orders
                    boolean flag3 = true;
                    do {
                        System.out.println("Please choose the option:\n" +
                                "1. Confirm arrival\n" +
                                "2. Check total spending last month\n" +
                                "3. Return");
                        int option3 = in.nextInt();
                        switch (option3){
                            case 1:
                                System.out.print("Please input the order id to confirm arrival: ");
                                int id = in.nextInt();
                                // 确认收货
                                System.out.println("Confirm successfully.");
                                break;
                            case 2:
                                // 上个月的消费总额
                                System.out.println("Your total spending last month is: ");
                                break;
                            case 3:
                                flag3 = false;
                                break;
                            default:
                                System.out.println("Wrong input. Please input again.");
                        }
                    } while (flag3);
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong input. Please input again.");
            }
        } while(flag);
    }

    public static void showGoods(ArrayList<Goods> goods){
        System.out.println(String.format("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s", "Goods id", "Goods Name",
                "Price", "Discount", "Brand", "Origin Place", "Preserve Time", "Volume", "Frozen", "Category", "Type"));
        for (Goods x : goods) {
            System.out.println(String.format("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s", x.getGoodsId(), x.getName(), x.getPrice(), x.getDiscount(),
                    x.getBrand(), x.getOriginPlace(), x.getPreserveTime(), x.getVolume(), x.getRefrigiratedCondition(), x.getCatagory(), x.getType()));
        }
    }

    public static void modify(Customer customer){
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
                            "4. Address\n" +
                            "5. Return");
                    int option2 = in.nextInt();
                    switch (option2){
                        case 1:
                            System.out.print("Please input your new username: ");
                            customer.setUserName(in.next());
                            break;
                        case 2:
                            System.out.print("Please input your old password: ");
                            if(in.next().equals(customer.getPassword())){
                                System.out.print("Please input your new password: ");
                                customer.setPassword(in.next());
                            } else {
                                System.out.println("Your input password is wrong.");
                            }
                            break;
                        case 3:
                            System.out.print("Please input your new phone number: ");
                            customer.setPhoneNumber(in.next());
                            break;
                        case 4:
                            System.out.print("Please input your new address: ");
                            customer.setAddress(in.next());
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

}
