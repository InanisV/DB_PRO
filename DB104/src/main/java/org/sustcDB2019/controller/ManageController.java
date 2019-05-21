package org.sustcDB2019.controller;
import java.io.UnsupportedEncodingException;
import org.sustcDB2019.entity.*;
import org.sustcDB2019.service.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageController {
    public static Scanner in = new Scanner(System.in);
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DOUBLE_CHAR_REG = "[^\\x00-\\xff]";

    public static void ManagerView(int managerid){
        ManagerService managerService = new ManagerService();
        managerService.manager = managerService.getManagerById(managerid);
        boolean flag = true;
        do {
            System.out.println("Please choose the option:\n" +
                    "1. View personal information\n" +
                    "2. Add new accounts\n" +
                    "3. Modify accounts information\n" +
                    "4. Check good sales \n" +
                    "5. Check capacity\n" +
                    "6. Manipulate and check goods\n" +
                    "7. Add new warehouse\n" +
                    "8. Log out");
            int option = in.nextInt();
            switch (option){
                case 1:
                    System.out.println("Your Personal Information：");
                    System.out.println("Username: " + managerService.manager.getUserName());
                    System.out.println("Phone number: " + managerService.manager.getPhoneNumber());
                    System.out.println("Warehouse id: " + managerService.manager.getWarehouseWarehouseId());
                    modify(managerService.manager);
                    break;
                case 2:
                    addAccount(managerService);
                    break;
                case 3:
                    modifyAccount(managerService);
                    break;
                case 4:
                    int page3 = 1;
                    boolean flag5 = true;
                    ArrayList<GoodsWithAmountIncome> goodsWithAmounts = managerService.getOrderedBySalesVolume(page3);
                    showGoodsWithAmount(goodsWithAmounts);
                    do {
                        System.out.println("Please choose the option:\n" +
                                "1. Next page\n" +
                                "2. Jump to page\n" +
                                "3. Return");
                        int option3 = in.nextInt();
                        switch (option3){
                            case 1:
                                page3 += 1;
                                goodsWithAmounts = managerService.getOrderedBySalesVolume(page3);
                                showGoodsWithAmount(goodsWithAmounts);
                                break;
                            case 2:
                                System.out.print("Please input the page number: ");
                                page3 = in.nextInt();
                                goodsWithAmounts = managerService.getOrderedBySalesVolume(page3);
                                showGoodsWithAmount(goodsWithAmounts);
                                break;
                            case 3:
                                flag5 = false;
                                break;
                            default:
                                System.out.println("Wrong input. Please input again.");
                        }
                    } while (flag5);
                    break;
                case 5:
                    int[] capacity = managerService.getRestVolume(managerService.manager.getWarehouseWarehouseId());
                    System.out.println("Remaining capacity of the regular warehouse is: " + capacity[0]);
                    System.out.println("Remaining capacity of the frozen warehouse is: " + capacity[1]);
                    break;
                case 6:
                    CustomerService customerService = new CustomerService();
                    int page2 = 1;
                    Goods g = new Goods();
                    String lowerPrice = null;
                    String upperPrice = null;
                    boolean discount = false;
                    String orderByPrice = null;
                    boolean orderByDiscount = false;
                    ArrayList<Goods> goods = customerService.goodsArrayListWithFilter(g, managerService.manager.getWarehouseWarehouseId(), lowerPrice, upperPrice, discount, orderByPrice, orderByDiscount, page2);
                    AdminController.showGoods(goods);
                    boolean flag2 = true;
                    do {
                        System.out.println("Please choose the option:\n" +
                                "1. Next page\n" +
                                "2. Jump to page\n" +
                                "3. Search goods\n" +
                                "4. Replenish goods\n" +
                                "5. Check discount goods\n" +
                                "6. Add or change discount\n" +
                                "7. Check coming expired goods\n" +
                                "8. Return");
                        int option2 = in.nextInt();
                        switch (option2){
                            case 1:
                                page2 += 1;
                                goods = customerService.goodsArrayListWithFilter(g,managerService.manager.getWarehouseWarehouseId(), lowerPrice, upperPrice, discount, orderByPrice, orderByDiscount, page2);
                                AdminController.showGoods(goods);
                                break;
                            case 2:
                                System.out.print("Please input the page number: ");
                                page2 = in.nextInt();
                                goods = customerService.goodsArrayListWithFilter(g,managerService.manager.getWarehouseWarehouseId(), lowerPrice, upperPrice, discount, orderByPrice, orderByDiscount, page2);
                                AdminController.showGoods(goods);
                                break;
                            case 3:
                                System.out.print("Please input the name of the goods: ");
                                g.setName(in.next());
                                goods = customerService.goodsArrayListWithFilter(g,managerService.manager.getWarehouseWarehouseId(), lowerPrice, upperPrice, discount, orderByPrice, orderByDiscount, page2);
                                AdminController.showGoods(goods);
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
                                            Goods g2 = new Goods();
                                            System.out.print("Please input the good name: ");
                                            g2.setName(in.next());
                                            System.out.print("Please input the price: ");
                                            g2.setPrice(in.nextBigDecimal());
                                            System.out.print("Please input the brand: ");
                                            g2.setBrand(in.next());
                                            System.out.print("Please input the discount: ");
                                            g2.setDiscount(in.nextBigDecimal());
                                            System.out.print("Please input 'Y' for frozen or 'N' for not frozen: ");
                                            g2.setRefrigiratedCondition(in.next());
                                            System.out.print("Please input the origin place: ");
                                            g2.setOriginPlace(in.next());
                                            System.out.print("Please input the type: ");
                                            g2.setType(in.next());
                                            System.out.print("Please input the preserve time: ");
                                            g2.setPreserveTime(in.nextInt());
                                            System.out.print("Please input the volume: ");
                                            g2.setVolume(in.nextInt());
                                            System.out.print("Please input the category: ");
                                            g2.setCatagory(in.next());
                                            System.out.print("Please input the amount: ");
                                            int amount2 = in.nextInt();
                                            g2 = managerService.addNewGoods(g2);
                                            managerService.purchaseToWarehouse(g2.getGoodsId(), amount2, new Date());
                                            System.out.println("Replenish successfully.");
                                            break;
                                        case 2:
                                            System.out.print("Please input the good id: ");
                                            int id = in.nextInt();
                                            System.out.print("Please input the amount: ");
                                            int amount = in.nextInt();
                                            Date date = new Date();
                                            managerService.purchaseToWarehouse( id, amount, date);
                                            System.out.println("Replenish successfully.");
                                    }
                                } while (flag4);
                                break;
                            case 5:
                                page2 = 1;
                                discount = true;
                                goods = customerService.goodsArrayListWithFilter(g, managerService.manager.getWarehouseWarehouseId(),lowerPrice, upperPrice, discount, orderByPrice, orderByDiscount, page2);
                                AdminController.showGoods(goods);
                                break;
                            case 6:
                                System.out.print("Please input the goods id to make discount: ");
                                int id2 = in.nextInt();
                                System.out.print("Please input the discount: ");
                                double discount2 = in.nextDouble();
                                managerService.changeGoodsDiscount(id2, discount2);
                                System.out.println("Discount successfully.");
                                break;
                            case 7:
                                ArrayList<GoodsInWarehouse> goodsInWarehouses = managerService.getNearOverdue();
                                showGoodsInWarehouse(goodsInWarehouses);
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
                    System.out.print("Please input the warehouse's address: ");
                    String address = in.next();
                    System.out.print("Please input the warehouse's refrigerated shelf volume: ");
                    int volume = in.nextInt();
                    System.out.print("Please input the warehouse's original shelf volume: ");
                    int volume2 = in.nextInt();
                    System.out.print("Please input the warehouse's longitude: ");
                    BigDecimal longi = in.nextBigDecimal();
                    System.out.print("Please input the warehouse's latitude: ");
                    BigDecimal lati = in.nextBigDecimal();
                    managerService.addNewWarehouse(address,volume, volume2, longi,lati);
                    break;
                case 8:
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong input. Please input again.");
            }
        } while(flag);
    }

    public static void addAccount(ManagerService managerService){
        UserService userService = new UserService();
        System.out.println("Please choose the identity you want to add:\n1. Manager\n2. Deliverer\n3. Cashier");
        int identity = in.nextInt();
        boolean exit = true;
        String name = null;
        while (exit){
            System.out.print("Please input the username: ");
            name = in.next();
            if(userService.userNameExist(name)==1){
                System.out.println("The username repeated, please input again.");
            } else {
                exit = false;
            }
        }
        System.out.print("Please input the password: ");
        String password = in.next();
        System.out.print("Please input the phone number: ");
        String phone = in.next();
        System.out.print("Please input the warehouse Id: ");
        int id = in.nextInt();
        if(identity==1){
            managerService.addNewManager(name, password, phone, id);
        } else if(identity==2){
            managerService.addNewDeliverer(name, password, phone, id);
        } else if(identity==3){
            managerService.addNewCashier(name, password, phone, id);
        }
        System.out.println("Add new account successfully.");
    }

    public static void modify(Manager manager){
        boolean flag = true;
        ManagerService managerService = new ManagerService();
        CustomerService customerService = new CustomerService();
        managerService.manager = manager;
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
                                    managerService.manager.setUserName(name);
                                }
                            } while (repite==1);
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
                            if(managerService.updateManager(manager)==0){
                                System.out.println("Modify successfully.");
                            } else {
                                System.out.println("Modification fails.");
                            }
                            flag3 = false;
                            break;
                        default:
                            System.out.println("Your input is wrong, please input again.");
                    }
                } while (flag3);
            } else {
                flag = false;
            }
        } while (flag);
    }

    public static int modifyAccount(ManagerService managerService){
        System.out.print("Please input the username of the account to modify: ");
        User user = managerService.getUserByName(in.next());

        int identity = user.getId()/1000000;
        if(identity==2){
            Manager manager = managerService.getManagerById(user.getId());
            modify(manager);
        } else if(identity==6){
            Deliverer deliverer = managerService.getDelivererById(user.getId());
            DelivererController.modify(deliverer);
        } else if(identity==30){
            Customer customer = managerService.getCustomerById(user.getId());
            AdminController.modify(customer);
        } else if(identity==4){
            Cashier cashier = managerService.getCashierById(user.getId());
            CashierController.modify(cashier);
        } else {
            System.out.println("Your input username is wrong.");
        }
        return 0;
    }

    public static void showGoodsInWarehouse(ArrayList<GoodsInWarehouse> goodsInWarehouses){
        System.out.println(String.format("%-11s%-11s%-7s%-15s", "Batch Id",
                "Goods id", "Amount", "Expired day"));
        for (GoodsInWarehouse x : goodsInWarehouses) {
            System.out.println(String.format("%-11s%-11s%-7s%-15s", x.getIdgoodsInWarehouse(), x.getGoodsGoodsId(),
                    x.getAmount(), x.getExpiredDay()));
        }
    }

    public static void showGoodsWithAmount(ArrayList<GoodsWithAmountIncome> goodsWithAmounts){
        System.out.println(String.format("%-9s%-101s%-13s%-10s%-12s%-36s%-36s%-16s%-10s%-10s%-16s%-15s", "Good id", "Goods Name", "Sale amount",
                "Price", "Discount", "Brand", "Origin Place", "Preserve Time", "Volume", "Frozen", "Category", "Type"));
//        for (GoodsWithAmountIncome x : goodsWithAmounts) {
//            System.out.println(String.format("%-10s\t%-130s%-13s\t%-10s\t%-12s\t%-35s\t%-36s\t%-10s\t%-10s\t%-10s\t%-5s\t", x.getGoodsId(), expend(x.getName(),130), x.getAmount(), x.getPrice(), x.getDiscount(),
//                    x.getBrand(), x.getOriginPlace(), x.getPreserveTime(), x.getVolume(), x.getRefrigiratedCondition(), x.getCatagory(), x.getType()));
//        }
        for (GoodsWithAmountIncome x : goodsWithAmounts) {
            System.out.println(String.format("%-9s",x.getGoodsId()) + "" + expend(x.getName(),100)+ "" + String.format("%-13s",x.getAmount())+ ""
                    + String.format("%-10s",x.getPrice())+ "" + String.format("%-12s",x.getDiscount())+ "" +
                    expend(x.getBrand(),35)+ "" + expend(x.getOriginPlace(),35)+ "" + String.format("%-16s",x.getPreserveTime())+ ""
                    + String.format("%-10s",x.getVolume())+ "" + String.format("%-10s",x.getRefrigiratedCondition())+ ""
                    + expend(x.getCatagory(),16)+ "" + expend(x.getType(),15));
        }
    }

    public static String expend(String str, int num){
        int le = count(str);
        for (int i = le; i <= num; i++) {
            str += " ";
        }
        return str;
    }

    public static int count(String str){
        int len = str.length();
        Matcher matcher = Pattern.compile(DOUBLE_CHAR_REG).matcher(str);
        while (matcher.find()) {
            len++;//双字节长度+1
        }
        return len;
    }
}
