package com.dfn.autotrader;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.dfn.autotrader.actors.SupervisorActor;
import com.dfn.autotrader.fix.FixSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by darshanas on 11/25/2017.
 */
public class StartAutoTrader {

    private static final Logger logger = LogManager.getLogger(StartAutoTrader.class);

    public static void main(String[] args) {
        FixSession fixSession = FixSession.getFixSession();
        ActorSystem system = ActorSystem.create("broker");
        system.actorOf(Props.create(SupervisorActor.class),"systemSupervisor");
    }

}
