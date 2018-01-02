package com.dfn.autotrader.actors;

import akka.actor.UntypedActor;
import com.dfn.autotrader.fix.FixSession;
import com.dfn.autotrader.store.PriceStore;
import quickfix.field.*;
import quickfix.fix42.NewOrderSingle;

import java.text.ParseException;

/**
 * Created by darshanas on 12/21/2017.
 */
public class UpTreandsMaker extends UntypedActor{

    private String symbol;

    public UpTreandsMaker(String symbol){
        this.symbol = symbol;
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("Starting Up trands supervisor");
        super.preStart();
        startTrading();
    }

    @Override
    public void onReceive(Object message) throws Exception {

    }


    private void startTrading() throws InterruptedException {


        for(int i = 1; i < 50; i++){

            double ltp = PriceStore.getLastTradedPrice(symbol);
            if(ltp == 0)
                ltp = 100;

            if(i%2 != 0){
                startUpMinorCycle(ltp);
            }else {
                startDownMinorCycle(ltp);
            }

            Thread.sleep(1000);

        }

    }




    private void startUpMinorCycle(double ltp) throws InterruptedException {

        System.out.println("Start Up minor cycle");
        double lastOrdPrice = ltp;
        double totQty = 0;

        for(int i = 0; i < 15; i++){
            lastOrdPrice = lastOrdPrice + 0.2;
            System.out.println("Price " + lastOrdPrice);
            totQty = totQty + 100;
            if(i == 14){
                sendMktOrder(Side.SELL, totQty);
            }else {
                sendLimitOrder(Side.BUY,100,lastOrdPrice);
            }
            Thread.sleep(1000);
        }

    }

    private void startDownMinorCycle(double ltp) throws InterruptedException {

        System.out.println("Start Down minor cycle");
        double lastOrdPrice = ltp;
        double totQty = 0;

        for(int i = 0; i < 8; i++){
            lastOrdPrice = lastOrdPrice - 0.2;
            System.out.println("Price " + lastOrdPrice);
            totQty = totQty + 100;
            if(i == 14){
                sendMktOrder(Side.BUY, totQty);
            }else {
                sendLimitOrder(Side.SELL,100,lastOrdPrice);
            }
            Thread.sleep(1000);
        }

    }

    private void sendLimitOrder(char side, double qty, double price){

        NewOrderSingle order = new NewOrderSingle();
        order.set(new ClOrdID(Long.toString(System.nanoTime())));
        order.set(new HandlInst('3'));
        order.set(new Symbol(symbol));
        order.set(new Side(side));
        order.set(new OrdType(OrdType.LIMIT));
        order.set(new Price(price));
        order.set(new TransactTime());
        order.set(new TimeInForce(TimeInForce.DAY));
        order.set(new OrderQty(qty));
        order.set(new Account("1111"));
        FixSession.sendFixMessage(order);

    }

    private void sendMktOrder(char side, double qty){

        NewOrderSingle order = new NewOrderSingle();
        order.set(new ClOrdID(Long.toString(System.nanoTime())));
        order.set(new HandlInst('3'));
        order.set(new Symbol(symbol));
        order.set(new Side(side));
        order.set(new OrdType(OrdType.MARKET));
        order.set(new TransactTime());
        order.set(new TimeInForce(TimeInForce.DAY));
        order.set(new OrderQty(qty));
        order.set(new Account("1111"));
        FixSession.sendFixMessage(order);

    }



}
