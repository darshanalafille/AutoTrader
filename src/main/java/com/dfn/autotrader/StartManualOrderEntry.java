package com.dfn.autotrader;

import com.dfn.autotrader.fix.FixSession;
import com.dfn.autotrader.store.AdminStore;
import quickfix.field.*;
import quickfix.fix42.NewOrderSingle;

import java.util.Scanner;

public class StartManualOrderEntry {



    public static void main(String[] args) {
        FixSession fixSession = FixSession.getFixSession();
        Scanner scanner = new Scanner(System.in);
        waitTillLogin();
        while (true){
            System.out.println("Enter You Command");
            String command = scanner.nextLine();
            if(command.equals("new")){
                NewOrderSingle newOrderSingle = getNewOrder(scanner);
                FixSession.sendFixMessage(newOrderSingle);
            }
        }
    }

    private static void waitTillLogin(){
        while (true){
            if(AdminStore.getLoginStatus())
                return;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static NewOrderSingle getNewOrder(Scanner scanner){
        NewOrderSingle order = new NewOrderSingle();
        order.set(new ClOrdID(Long.toString(System.nanoTime())));
        order.set(new HandlInst('3'));
        System.out.print("Symbol : ");
        String symbol = scanner.nextLine();
        order.set(new Symbol(symbol));
        System.out.print("Side (1 - Buy, 2 - Sell) : ");
        String side = scanner.nextLine();
        order.set(new Side(side.equals("1") ? Side.BUY : Side.SELL));
        System.out.print("Order Type (Market - 1, Limit - 2) : ");
        String ordType = scanner.nextLine();
        order.set(new OrdType(ordType.equals("1") ? OrdType.MARKET : OrdType.LIMIT));
        order.set(new TransactTime());
        order.set(new TimeInForce(TimeInForce.DAY));
        System.out.print("Order Qty : ");
        order.set(new OrderQty(scanner.nextDouble()));
        order.set(new Account("11511511110"));
        if(ordType.equals("2")){
            System.out.print("Order Price : ");
            order.set(new Price(scanner.nextDouble()));
        }
        return order;
    }

}
