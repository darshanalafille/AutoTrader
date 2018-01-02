package com.dfn.autotrader.actors;

import akka.actor.Cancellable;
import akka.actor.UntypedActor;
import com.dfn.autotrader.entity.Config;
import com.dfn.autotrader.entity.MinMax;
import com.dfn.autotrader.entity.Trader;
import com.dfn.autotrader.fix.FixSession;
import com.dfn.autotrader.messages.WakeUp;
import quickfix.field.*;
import quickfix.fix42.NewOrderSingle;
import scala.concurrent.duration.Duration;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by darshanas on 11/26/2017.
 */
public class TraderActor extends UntypedActor{


    private Trader trader;
    Cancellable cancellable = null;
    Random random = new Random();
    List<String> symList  = null;



    @Override
    public void preStart() throws Exception {
        super.preStart();
        System.out.println("Trader " + trader.getPortfolio().getAccountNo() + " has been created.");
    }

    public TraderActor(Trader trader){
        this.trader = trader;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof WakeUp){
            FixSession.sendFixMessage(getOrder());
        }else if(message instanceof String){
            String string = (String)message;
            if(string.equals("start")){
                cancellable = getContext().system().scheduler().schedule(
                        Duration.apply(2,TimeUnit.SECONDS),
                        Duration.create(trader.getDelayInSec(), TimeUnit.SECONDS),getSelf(),new WakeUp(),getContext().dispatcher(),null
                );
            }else if(string.equals("stop")){
                cancellable.cancel();
            }
        }
    }


    private NewOrderSingle getOrder(){
        NewOrderSingle order = new NewOrderSingle();
        order.set(new ClOrdID(Long.toString(System.nanoTime()) + trader.getTraderId()));
        order.set(new HandlInst('3'));
        String symbol = pickSymbol();
        order.set(new Symbol(symbol));
        order.set(new Side(pickSide()));
        char type = pickType();
        order.set(new OrdType(type));
        if(type == OrdType.LIMIT){
            try {
                order.set(new Price(pickPrice(symbol)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        order.set(new TransactTime());
        order.set(new TimeInForce(TimeInForce.DAY));
        order.set(new OrderQty((double)pickQty()));
        order.set(new Account(trader.getPortfolio().getAccountNo()));
        return order;

    }

    private char pickSide(){
        int index = random.nextInt();
        if(index % 2 == 0){
            return Side.SELL;
        }else {
            return Side.BUY;
        }
    }

    private char pickType(){
        int index = random.nextInt(100);
//        return OrdType.LIMIT;
        if(index > 72){
            return OrdType.MARKET;
        }else {
            return OrdType.LIMIT;
        }
    }

    private String pickSymbol(){
        if(symList == null){
            symList = SymbolStore.getSymbolList();
        }
        int index = random.nextInt(35);
        //int index = random.nextInt(3);
        if(index < symList.size()){
            return symList.get(index);
        }else {
            return symList.get(0);
        }
    }




    private double pickPrice(String symbol) throws ParseException {
        MinMax minMax = SymbolStore.getMinMax(symbol);
        Double highPrice = minMax.getMax();
        Double lawPrice = minMax.getMin();
        if(highPrice == lawPrice){
            return minMax.getMax();
        }else {
            int hpInt = highPrice.intValue();
            int lpInt = lawPrice.intValue();
            int rVan = random.nextInt(hpInt + 1 - lpInt) + lpInt;
            double x = rVan + random.nextDouble();
            DecimalFormat f = new DecimalFormat("##.00");
            String format = f.format(x);
            //System.out.println("Format " + format);
            Number number = f.parse(format);
            return number.doubleValue();
        }
    }

    private int pickQty(){
        int q = random.nextInt(50000);
        if(q == 0){
            return 25;
        }
        return q;
    }


}
