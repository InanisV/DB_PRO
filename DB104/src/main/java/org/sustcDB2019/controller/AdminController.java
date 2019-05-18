package org.sustcDB2019.controller;

import org.sustcDB2019.entity.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AdminController {
    public static Scanner in = new Scanner(System.in);
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void CustomerView(User user){
        Customer customer = new Customer(); // 传入userid
        // 加载个人信息
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
                    System.out.println("Your Personal Information");

                    MainController.modify(user);
                    break;
                case 2:
                    // Display goods information
                    // 输入页码 返回goods的arraylist
                    int page  = 1;
                    ArrayList<Goods> goods = new ArrayList<Goods>();
                    boolean flag2 = true;
                    do {
                        System.out.println("Please choose the option:\n" +
                                "1. Next page\n" +
                                "2. Jump to page\n" +
                                "3. Search goods\n" +
                                "3. Add goods to cart\n" +
                                "4. Check the cart and purchase\n" +
                                "5. Return");
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
                                System.out.print("Please input the goods id: ");
                                int id = in.nextInt();
                                System.out.print("Please input the amount: ");
                                int amount = in.nextInt();
                                // pass in id and quantity to add to the cart
                                break;
                            case 4:
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
                            case 5:
                                flag2 = false;
                            default:
                                System.out.println("Wrong input. Please input again.");
                        }
                    } while (flag2);
                    break;
                case 3:
                    // show historical orders

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

    }

}
