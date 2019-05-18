package org.sustcDB2019.controller;

import org.sustcDB2019.entity.Deliverer;
import org.sustcDB2019.entity.User;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DelivererController {
    public static Scanner in = new Scanner(System.in);
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void DelivererView(User user){

        boolean flag = true;
        do {
            System.out.println("Please choose the option:\n" +
                    "1. View personal information\n" +
                    "2. View order tasks\n" +
                    "3. Log out");
            int option = in.nextInt();
            switch (option){
                case 1:
//                    System.out.println("Your Personal Information：");
//                    System.out.println("Username: " + DelivererService.deliverer.getUserName());
//                    System.out.println("Phone number: " + DelivererService.deliverer.getPhoneNumber());
//                    System.out.println("Warehouse id: " + DelivererService.deliverer.getWarehouseWarehouseId());
//                    modify(DelivererService.deliverer);
                case 2:
                    // show orders information
//                    boolean flag2 = true;
//                    do {
//                        System.out.println("Please choose the option:\n1. Confirm arrival of the delivery order\n2. Accept order\n3. Return");
//                        int option2 = in.nextInt();
//                        switch (option2){
//                            case 1:
//                                System.out.print("Please input the order id to confirm: ");
//                                int id = in.nextInt();
//                                df.format(new Date());
//                                // pass id and time to call the method to change the order info
//                                System.out.println("Confirm successfully.");
//                                break;
//                            case 2:
//                                System.out.print("Please input the order id to accept: ");
//                                int id2 = in.nextInt();
//                                df.format(new Date());
//                                // pass id and time to call the method to change the order info
//                                System.out.println("Accept successfully.");
//                                break;
//                            case 3:
//                                flag2 = false;
//                                break;
//                            default:
//                                System.out.println("Wrong input. Please input again.");
//                        }
//                    } while (flag2);
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong input. Please input again.");
            }
        } while(flag);
    }

    public static void modify(Deliverer deliverer){
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
                            deliverer.setUserName(in.next());
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
