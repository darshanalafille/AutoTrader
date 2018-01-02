package com.dfn.autotrader.store;

import com.dfn.autotrader.entity.price.PriceEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by darshanas on 12/21/2017.
 */
public class PriceStore {

    private static final Map<String,PriceEvent> map = new HashMap<>();

    public static void addPriceEvent(String symbol, PriceEvent event){
        map.put(symbol,event);
    }

    public static PriceEvent getLastPriceEvent(String symbol){
        return map.get(symbol);
    }

    public static double getLastTradedPrice(String symbol){
        PriceEvent e = map.get(symbol);
        if(e != null){
            return e.getLastTradePrice();
        }
        return 0;
    }

}
