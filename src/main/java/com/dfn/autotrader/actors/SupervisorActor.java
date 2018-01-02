package com.dfn.autotrader.actors;

import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.dfn.autotrader.entity.Config;
import com.dfn.autotrader.messages.WakeUp;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by darshanas on 11/25/2017.
 */
public class SupervisorActor extends UntypedActor {


    Cancellable cancellable = null;
    private boolean isTrading = false;
    private String command;

    public SupervisorActor(String command){
        this.command = command;
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        if(command.equals("random")){
            Config.getTraderList2().forEach((trader) -> {
                ActorRegistry.put(trader.getPortfolio().getAccountNo(), getContext().actorOf(Props.create(TraderActor.class, trader),
                        trader.getPortfolio().getAccountNo()));
            });
            ActorRef feed = getContext().actorOf(Props.create(FeedHandler.class),"feedHandler");
            cancellable = getContext().system().scheduler().scheduleOnce(Duration.create(5, TimeUnit.SECONDS),
                    getSelf(),new WakeUp(),getContext().dispatcher(),null);
        }else if(command.equals("up")){
            ActorRef ref = getContext().actorOf(Props.create(UpTreandsMaker.class,"1080"),"UpTreandsMaker");
            ActorRef feed = getContext().actorOf(Props.create(FeedHandler.class),"feedHandler");
        }else if(command.equals("down")){
            ActorRef ref = getContext().actorOf(Props.create(DownTreandsMaker.class,"1080"),"DownTreandsMaker");
            ActorRef feed = getContext().actorOf(Props.create(FeedHandler.class),"feedHandler");
        }
    }

    @Override
    public void onReceive(Object message) throws Exception {

        if(message instanceof WakeUp){
            if(isTrading == true){
               ActorRegistry.getAllActors().forEach(a -> a.tell("stop",getSelf()));
                isTrading = false;
                cancellable = getContext().system().scheduler().scheduleOnce(Duration.create(20, TimeUnit.SECONDS),
                        getSelf(),new WakeUp(),getContext().dispatcher(),null);
            }else {
                ActorRegistry.getAllActors().forEach(a -> a.tell("start",getSelf()));
                isTrading = true;
                cancellable = getContext().system().scheduler().scheduleOnce(Duration.create(3, TimeUnit.MINUTES),
                        getSelf(),new WakeUp(),getContext().dispatcher(),null);
            }
        }

    }
}
