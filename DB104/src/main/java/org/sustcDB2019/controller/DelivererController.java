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
        Deliverer deliverer = new Deliverer();  // Load deliverer's personal information
        boolean flag = true;
        do {
            System.out.println("Please choose the option:\n" +
                    "1. View personal information\n" +
                    "2. View order lists\n" +
                    "3. Log out");
            int option = in.nextInt();
            switch (option){
                case 1:
                    System.out.println("Information");// show deliverer's personal information
//                    MainController.modify(deliverer);
                case 2:
                    // show orders information
                    boolean flag2 = true;
                    do {
                        System.out.println("Please choose the option:\n1. Confirm arrival of the delivery order\n2. Accept order\n3. Return");
                        int option2 = in.nextInt();
                        switch (option2){
                            case 1:
                                System.out.print("Please input the order id to confirm: ");
                                int id = in.nextInt();
                                df.format(new Date());
                                // pass id and time to call the method to change the order info
                                System.out.println("Confirm successfully.");
                                break;
                            case 2:
                                System.out.print("Please input the order id to accept: ");
                                int id2 = in.nextInt();
                                df.format(new Date());
                                // pass id and time to call the method to change the order info
                                System.out.println("Accept successfully.");
                                break;
                            case 3:
                                flag2 = false;
                                break;
                            default:
                                System.out.println("Wrong input. Please input again.");
                        }
                    } while (flag2);
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong input. Please input again.");
            }
        } while(flag);
    }

}
