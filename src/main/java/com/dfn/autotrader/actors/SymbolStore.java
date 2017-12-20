package com.dfn.autotrader.actors;

import com.dfn.autotrader.entity.MinMax;
import com.dfn.autotrader.entity.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by darshanas on 11/26/2017.
 */
public class SymbolStore {

    private static Map<String,Symbol> symbolMap = new HashMap<>();
    private static Map<String,MinMax> minMaxMap = new HashMap<>();


    static {
        minMaxMap.put("1080",new MinMax(100.25,102.60));
        minMaxMap.put("2070",new MinMax(563,590));
        minMaxMap.put("2090",new MinMax(10,15));
        minMaxMap.put("1120",new MinMax(360,362));
        minMaxMap.put("2110",new MinMax(100,115));
        minMaxMap.put("1020",new MinMax(215.50,290));
        minMaxMap.put("2010",new MinMax(7.50,11.78));
        minMaxMap.put("1140",new MinMax(63,65));
        minMaxMap.put("1040",new MinMax(260,261.50));
        minMaxMap.put("2030",new MinMax(1050,1060));
        minMaxMap.put("1060",new MinMax(35,37));
        minMaxMap.put("2050",new MinMax(75,92));
    }

    public static void put(Symbol symbol){
        symbolMap.put(symbol.getName(),symbol);
    }

    public static Symbol getSymbol(String name){
        return symbolMap.get(name);
    }

    public static List<String> getSymbolList(){
        List<String> list = new ArrayList<>();
        symbolMap.forEach((k,v) -> {
            list.add(v.getName());
        });
        return list;
    }

    public static MinMax getMinMax(String symbol){
        return minMaxMap.get(symbol);
    }




}
