package com.dfn.autotrader.actors;

import akka.actor.ActorRef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by darshanas on 11/26/2017.
 */
public class ActorRegistry {

    private static Map<String,ActorRef> refMap = new HashMap<>();

    public static void put(String name,ActorRef ref){
        refMap.put(name,ref);
    }

    public static ActorRef get(String name){
        return refMap.get(name);
    }

    public static List<ActorRef> getAllActors(){
        List<ActorRef> list = new ArrayList<>();
        refMap.forEach((k,v) -> list.add(v));
        return list;
    }

}
