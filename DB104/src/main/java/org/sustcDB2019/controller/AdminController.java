package org.sustcDB2019.controller;

import org.sustcDB2019.entity.*;
import org.sustcDB2019.service.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminController {
    public static Scanner in = new Scanner(System.in);
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final String DOUBLE_CHAR_REG = "[^\\x00-\\xff]";

    public static void CustomerView(int customerid){
        CustomerService customerService = new CustomerService();
        ManagerService managerService = new ManagerService();
        customerService.customer = managerService.getCustomerById(customerid);
        customerService.updateWarehouse();
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
                    System.out.println(customerService.customer.getWarehouseId());
                    modify(customerService.customer);
                    break;
                case 2:
                    int page  = 1;
                    ArrayList<Goods> goods = new ArrayList<Goods>();
                    Goods g = new Goods();
                    String lowerPrice = null;
                    String upperPrice = null;
                    boolean discount = false;
                    String orderByPrice = null;
                    boolean orderByDiscount = false;
                    boolean flag2 = true;
                    goods = customerService.goodsArrayListWithFilter(g, customerService.customer.getWarehouseId(),lowerPrice, upperPrice, discount, orderByPrice, orderByDiscount, page);
                    showGoods(goods);
                    do {
                        System.out.println("Please choose the option:\n" +
                                "1. Next page\n" +
                                "2. Jump to page\n" +
                                "3. Add condition to search goods\n" +
                                "4. Delete condition to search\n" +
                                "5. Add goods to cart\n" +
                                "6. Check the cart and purchase\n" +
                                "7. Return");
                        int option2 = in.nextInt();
                        switch (option2){
                            case 1:
                                page += 1;
                                goods = customerService.goodsArrayListWithFilter(g, customerService.customer.getWarehouseId(),lowerPrice, upperPrice, discount, orderByPrice, orderByDiscount, page);
                                showGoods(goods);
                                break;
                            case 2:
                                System.out.print("Please input the page number: ");
                                page = in.nextInt();
                                goods = customerService.goodsArrayListWithFilter(g, customerService.customer.getWarehouseId(),lowerPrice, upperPrice, discount, orderByPrice, orderByDiscount, page);
                                showGoods(goods);
                                break;
                            case 3:
                                System.out.println("Please choose the option:\n" +
                                        "1. By name\n" +
                                        "2. By category\n" +
                                        "3. By type\n" +
                                        "4. By price\n" +
                                        "5. Ordered by price\n" +
                                        "6. By brand\n" +
                                        "7. By origin place\n" +
                                        "8. By discounted\n" +
                                        "9. Ordered by discount\n" +
                                        "10. By refrigeration condition");
                                int option4 = in.nextInt();
                                switch (option4){
                                    case 1:
                                        System.out.print("Please input the name: ");
                                        g.setName(in.next());
                                        break;
                                    case 2:
                                        System.out.print("Please input the category: ");
                                        g.setCatagory(in.next());
                                        break;
                                    case 3:
                                        System.out.print("Please input the type: ");
                                        g.setType(in.next());
                                        break;
                                    case 4:
                                        System.out.print("Please input the lower price: ");
                                        lowerPrice = in.next();
                                        System.out.print("Please input the upper price: ");
                                        upperPrice = in.next();
                                        break;
                                    case 5:
                                        System.out.println("Please choose the option:\n1. Ordered increasingly\n2. Ordered decreasingly");
                                        int order = in.nextInt();
                                        if(order==1){
                                            orderByPrice = "";
                                        } else if(order==2){
                                            orderByPrice = "";
                                        } else {
                                            System.out.println("Wrong input.");
                                        }
                                        break;
                                    case 6:
                                        System.out.print("Please input the brand: ");
                                        g.setBrand(in.next());
                                        break;
                                    case 7:
                                        System.out.print("Please input the origin place: ");
                                        g.setOriginPlace(in.next());
                                        break;
                                    case 8:
                                        discount = true;
                                        break;
                                    case 9:
                                        orderByDiscount = true;
                                        break;
                                    case 10:
                                        System.out.println("Please choose the option:\n1. Refrigeration\n2. Not refrigeration");
                                        int re = in.nextInt();
                                        if(re==1){
                                            g.setRefrigiratedCondition("Y");
                                        } else if(re==2){
                                            g.setRefrigiratedCondition("N");
                                        } else{
                                            System.out.println("Wrong input.");
                                        }
                                        break;
                                }
                                goods = customerService.goodsArrayListWithFilter(g, customerService.customer.getWarehouseId(),lowerPrice, upperPrice, discount, orderByPrice, orderByDiscount, page);
                                showGoods(goods);
                                break;
                            case 4:
                                System.out.println("Please choose the option:\n" +
                                        "1. Delete name\n" +
                                        "2. Delete category\n" +
                                        "3. Delete type\n" +
                                        "4. Delete price\n" +
                                        "5. Delete brand\n " +
                                        "6. Delete origin place\n" +
                                        "7. Delete discounted\n" +
                                        "8. Delete refrigeration condition");
                                int option5 = in.nextInt();
                                switch (option5){
                                    case 1:
                                        g.setName(null);
                                        break;
                                    case 2:
                                        g.setCatagory(null);
                                        break;
                                    case 3:
                                        g.setType(null);
                                        break;
                                    case 4:
                                        upperPrice = null;
                                        lowerPrice = null;
                                        break;
                                    case 5:
                                        g.setBrand(null);
                                        break;
                                    case 6:
                                        g.setOriginPlace(null);
                                        break;
                                    case 7:
                                        discount = false;
                                        break;
                                    case 8:
                                        g.setRefrigiratedCondition(null);
                                        break;
                                }
                                goods = customerService.goodsArrayListWithFilter(g, customerService.customer.getWarehouseId(),lowerPrice, upperPrice, discount, orderByPrice, orderByDiscount, page);
                                showGoods(goods);
                                break;
                            case 5:
                                System.out.print("Please input the goods id: ");
                                int id = in.nextInt();
                                System.out.print("Please input the amount: ");
                                int amount = in.nextInt();
                                customerService.addToCart(id, amount);
                                System.out.println("Add successfully.");
                                break;
                            case 6:
                                ArrayList<Sales> sales = customerService.showCart(customerService.customer.getUserId());
                                ArrayList<ArrayList<Sales>> newSales = collectSales(sales);
                                showNewSales(newSales);
                                boolean flag3 = true;
                                do {
                                    System.out.println("Please choose the option:\n" +
                                            "1. Delete the goods\n" +
                                            "2. Buy now\n" +
                                            "3. Return");
                                    int option3 = in.nextInt();
                                    switch (option3){
                                        case 1:
                                            System.out.print("Please input the number to delete: ");
                                            int id2 = in.nextInt();
                                            customerService.cancleSales(newSales.get(id2-1));
                                            newSales.remove(id2-1);
                                            break;
                                        case 2:
                                            sales = customerService.showCart(customerService.customer.getUserId());
                                            customerService.buy(sales);
                                            System.out.println("Order generated successfully.");
                                            flag3 = false;
                                            break;
                                        case 3:
                                            flag3 = false;
                                            break;
                                        default:
                                            System.out.println("Wrong input. Please input again.");
                                    }
                                } while (flag3);
                            case 7:
                                flag2 = false;
                            default:
                                System.out.println("Wrong input. Please input again.");
                        }
                    } while (flag2);
                    break;
                case 3:
                    ArrayList<Order> orders = customerService.getOrder();
                    showOrders(orders);
                    boolean flag3 = true;
                    do {
                        System.out.println("Please choose the option:\n" +
                                "1. Check specific order\n" +
                                "2. Confirm arrival\n" +
                                "3. Check total spending by month\n" +
                                "4. Return");
                        int option3 = in.nextInt();
                        switch (option3){
                            case 1:
                                System.out.print("Please input the order number: ");
                                int number = in.nextInt();
                                if(number>orders.size()){
                                    System.out.println("Wrong input.");
                                } else{
                                    ArrayList<ArrayList<Sales>> newSales = collectSales(orders.get(number-1).getSaleses());
                                    showNewSales(newSales);
                                }
                                break;
                            case 2:
                                System.out.print("Please input the order id to confirm arrival: ");
                                int id = in.nextInt();
                                customerService.receiveOrder(id, new Date());
                                System.out.println("Confirm successfully.");
                                break;
                            case 3:
                                boolean test = false;
                                Date sdate = new Date();
                                Date edate = new Date();
                                String start = null;
                                String end = null;
                                do {
                                    System.out.print("Please input the start date with format yyyy-MM-dd: ");
                                    start = in.next();
                                    try {
                                        sdate = df.parse(start);
                                        test = false;
                                    } catch (Exception e) {
                                        System.out.println("Wrong input format.");
                                        test = true;
                                    }
                                } while (test);
                                do {
                                    System.out.print("Please input the end date with format yyyy-MM-dd: ");
                                    end = in.next();
                                    try {
                                        edate = df.parse(end);
                                        test = false;
                                    } catch (Exception e) {
                                        System.out.println("Wrong input format.");
                                        test = true;
                                    }
                                } while (test);
                                ArrayList<Integer> spend = customerService.getHistoryStatisticsByMonth(sdate, edate);
                                System.out.print("Your total spending during these month are: ");
                                for (Integer x: spend) {
                                    System.out.print(x + " ");
                                }
                                System.out.println();
                                break;
                            case 4:
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
//        System.out.println(String.format("%-10s%-45s%-8s%-12s%-15s%-16s%-8s%-8s%-8s%-5s", "Good id", "Goods Name",
//                "Price", "Discount", "Brand", "Origin Place", "Preserve Time", "Volume", "Frozen", "Category", "Type"));
//        for (Goods x : goods) {
//            System.out.println(String.format("%-10s%-45s%-8s%-12s%-15s%-16s%-8s%-8s%-8s%-5s", x.getGoodsId(), x.getName(), x.getPrice(), x.getDiscount(),
//                    x.getBrand(), x.getOriginPlace(), x.getPreserveTime(), x.getVolume(), x.getRefrigiratedCondition(), x.getCatagory(), x.getType()));
//        }
        System.out.println(String.format("%-9s%-101s%-10s%-12s%-36s%-36s%-16s%-10s%-10s%-16s%-15s", "Good id", "Goods Name",
                "Price", "Discount", "Brand", "Origin Place", "Preserve Time", "Volume", "Frozen", "Category", "Type"));
        for (Goods x : goods) {
            System.out.println(String.format("%-9s",x.getGoodsId()) + "" + expend(x.getName(),100)+ ""
                    + String.format("%-10s",x.getPrice())+ "" + String.format("%-12s",x.getDiscount())+ "" +
                    expend(x.getBrand(),35)+ "" + expend(x.getOriginPlace(),35)+ "" + String.format("%-16s",x.getPreserveTime())+ ""
                    + String.format("%-10s",x.getVolume())+ "" + String.format("%-10s",x.getRefrigiratedCondition())+ ""
                    + expend(x.getCatagory(),16)+ "" + expend(x.getType(),15));
        }
    }

    public static void modify(Customer customer){
        CustomerService customerService = new CustomerService();
        customerService.customer = customer;
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
                            int repite = 0;
                            do {
                                System.out.print("Please set your username: ");
                                String name = in.next();
                                repite = customerService.userNameExist(name);
                                if (repite!=0){
                                    System.out.println("The username repeated, please input again.");
                                } else {
                                    customerService.customer.setUserName(name);
                                }
                            } while (repite!=0);
                            break;
                        case 2:
                            System.out.print("Please input your old password: ");
                            if(in.next().equals(customerService.customer.getPassword())){
                                System.out.print("Please input your new password: ");
                                customerService.customer.setPassword(in.next());
                            } else {
                                System.out.println("Your input password is wrong.");
                            }
                            break;
                        case 3:
                            System.out.print("Please input your new phone number: ");
                            customerService.customer.setPhoneNumber(in.next());
                            break;
                        case 4:
                            System.out.print("Please input your new address: ");
                            customerService.customer.setAddress(in.next());
                            break;
                        case 5:
                            if(customerService.updateCustomer(customerService.customer)==0){
                                System.out.println("Modify successfully.");
                            } else {
                                System.out.println("Modification fails.");
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

    public static void showOrders(ArrayList<Order> orders){
        System.out.println(String.format("%-8s%-10s%-17s%-17s%-6s", "Number", "Order id", "Departure time",
                "Arrival Time", "Deliverer id"));
        int i = 1;
        for (Order x : orders) {
            System.out.println(String.format(String.format("%-8s%-10s%-17s%-17s%-6s", i,  x.getOrderId(), x.getDepartureTime(),
                    x.getArrivalTime(), x.getDeliveryUserId())));
            i++;
        }
    }

    public static void showSales(ArrayList<Sales> sales){
        for (Sales x : sales) {
            System.out.println(String.format("%-45s%-8s%-11s%-12s%-12s%-20s", x.getGoodsInWarehouse().getGoods().getName(), x.getGoodsInWarehouse().getGoods().getPrice(),
                    x.getAmount(), x.getGoodsInWarehouse().getGoods().getDiscount(), x.getPayment(), x.getIsPaid()));
        }
    }

    public static void showNewSales(ArrayList<ArrayList<Sales>> sales){
        System.out.println(String.format("%-8s%-101s%-8s%-11s%-12s%-12s%-20s", "Number", "Goods Name",
                "Price", "Amount", "Discount", "Payment", "Paid condition"));
        for (int i = 0; i < sales.size(); i++) {
            int amount = 0;
            BigDecimal payment = BigDecimal.valueOf(0);
            for (int j = 0; j < sales.get(i).size(); j++) {
                amount += sales.get(i).get(j).getAmount();
                payment.add(sales.get(i).get(j).getPayment());
            }
            System.out.println(String.format("%-8s%-45s%-8s%-11s%-12s%-12s%-20s", (i+1), expend(sales.get(i).get(0).getGoodsInWarehouse().getGoods().getName(), 100), sales.get(i).get(0).getGoodsInWarehouse().getGoods().getPrice(),
                    amount, sales.get(i).get(0).getGoodsInWarehouse().getGoods().getDiscount(), payment, sales.get(i).get(0).getIsPaid()));
        }
    }

    public static ArrayList<ArrayList<Sales>> collectSales(ArrayList<Sales> sales){
        ArrayList<ArrayList<Sales>> newSales = new ArrayList<ArrayList<Sales>>();
        for (int i = 0; i < sales.size(); i++) {
            boolean exit = false;
            c: for (int j = 0; j < newSales.size(); j++) {
                if(sales.get(i).getGoodsInWarehouse().getGoods().getName()==newSales.get(j).get(0).getGoodsInWarehouse().getGoods().getName()){
                    newSales.get(j).add(sales.get(i));
                    exit = true;
                    break c;
                }
            }
            if(!exit){
                newSales.add(new ArrayList<Sales>());
                newSales.get(newSales.size()-1).add(sales.get(i));
            }
        }
        return newSales;
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
