package org.sustcDB2019.controller;

import org.sustcDB2019.entity.*;
import org.sustcDB2019.service.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class CashierController {
    public static Scanner in = new Scanner(System.in);
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static void CashierView(int cashierid){
        CashierService cashierService = new CashierService();
        ManagerService managerService = new ManagerService();
        cashierService.cashier = managerService.getCashierById(cashierid);
        ArrayList<Sales> sales = new ArrayList<Sales>();
        boolean flag = true;
        do {
            System.out.println("Please choose the option:\n" +
                    "1. Add goods\n" +
                    "2. Delete goods\n" +
                    "3. Show goods list\n" +
                    "4. Buy now\n" +
                    "5. Return");
            int option2 = in.nextInt();
            switch (option2){
                case 1:
                    System.out.print("Please input the goods id: ");
                    int id = in.nextInt();
                    System.out.print("Please input the amount: ");
                    int amount = in.nextInt();
                    cashierService.addToCart(id, amount);
                    break;
                case 2:
                    System.out.print("Please input the goods id to delete: ");
                    int id2 = in.nextInt();
                    sales = cashierService.showCart(cashierService.cashier.getUserId());
                    ArrayList<Sales> de = new ArrayList<Sales>();
                    for (Sales x: sales) {
                        if(x.getGoodsInWarehouse().getGoods().getGoodsId()==id2){
                            de.add(x);
                        }
                    }
                    cashierService.cancleSales(de);
                    break;
                case 3:
                    sales = cashierService.showCart(cashierService.cashier.getUserId());
                    ArrayList<ArrayList<Sales>> newSales = AdminController.collectSales(sales);
                    AdminController.showNewSales(newSales);
                    break;
                case 4:
                    sales = cashierService.showCart(cashierService.cashier.getUserId());
                    cashierService.buyOffline(sales);
                    flag = false;
                    break;
                case 5:
                    flag = false;
                default:
                    System.out.println("Your input is wrong, please input again.");
                    break;
            }
        } while (flag);
    }

    public static void modify(Cashier cashier){
        boolean flag = true;
        ManagerService managerService = new ManagerService();
        CashierService cashierService = new CashierService();
        CustomerService customerService = new CustomerService();
        cashierService.cashier = managerService.getCashierById(cashier.getUserId());
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
                            int repite = 0;
                            do {
                                System.out.print("Please set your username: ");
                                String name = in.next();
                                repite = customerService.userNameExist(name);
                                if (repite==1){
                                    System.out.println("The username repeated, please input again.");
                                } else {
                                    cashierService.cashier.setUserName(name);
                                }
                            } while (repite==1);
                            break;
                        case 2:
                            System.out.print("Please input your old password: ");
                            if(in.next().equals(cashier.getPassword())){
                                System.out.print("Please input your new password: ");
                                cashier.setPassword(in.next());
                            } else {
                                System.out.println("Your input password is wrong.");
                            }
                            break;
                        case 3:
                            System.out.print("Please input your new phone number: ");
                            cashier.setPhoneNumber(in.next());
                            break;
                        case 4:
                            System.out.print("Please input your new warehouse id: ");
                            cashier.setWarehouseWarehouseId(in.nextInt());
                            break;
                        case 5:
                            if(cashierService.updateCasher(cashier)==0){
                                System.out.println("Modify successfully.");
                            } else {
                                System.out.println("Modification fails.");
                            }
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