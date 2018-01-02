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
        minMaxMap.put("1180",new MinMax(100,106));
        minMaxMap.put("1810",new MinMax(315.75,318.32));
        minMaxMap.put("1214",new MinMax(12.50,12.95));
        minMaxMap.put("1213",new MinMax(25,30));
        minMaxMap.put("1212",new MinMax(7.96,15.63));
        minMaxMap.put("1211",new MinMax(444,448));
        minMaxMap.put("1210",new MinMax(74.30,76.21));
        minMaxMap.put("2002",new MinMax(1056,1059.23));
        minMaxMap.put("1090",new MinMax(74,75));
        minMaxMap.put("2080",new MinMax(89,91));
        minMaxMap.put("1330",new MinMax(100,100.50));
        minMaxMap.put("2001",new MinMax(175.50,185.56));
        minMaxMap.put("2100",new MinMax(96,98));
        minMaxMap.put("1010",new MinMax(49,56));
        minMaxMap.put("2120",new MinMax(225,227));
        minMaxMap.put("1030",new MinMax(321,325));

        minMaxMap.put("2020",new MinMax(36,36.36));
        minMaxMap.put("1150",new MinMax(369,373));
        minMaxMap.put("1050",new MinMax(400,415));
        minMaxMap.put("2040",new MinMax(7.60,10.25));
        minMaxMap.put("2060",new MinMax(200,205));
        minMaxMap.put("1304",new MinMax(96,99));
        minMaxMap.put("1303",new MinMax(163,166));

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
