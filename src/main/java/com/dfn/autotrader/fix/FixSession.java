package com.dfn.autotrader.fix;

import quickfix.*;

/**
 * Created by darshanas on 11/26/2017.
 */
public class FixSession {

    private static SocketInitiator socketInitiator = null;
    private static FixSession fixSession = null;

    public static FixSession getFixSession(){
        if(fixSession == null){
            fixSession = new FixSession();
        }
        return fixSession;
    }

    private FixSession(){
        try {
            System.out.println("Loading the Fix Session");
            SessionSettings sessionSettings = new SessionSettings("./initiatorSettings.txt");
            Application initiatorApplication = new TradeAppInitiator();
            FileStoreFactory fileStoreFactory = new FileStoreFactory(sessionSettings);
            FileLogFactory fileLogFactory = new FileLogFactory(sessionSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            socketInitiator = new SocketInitiator(initiatorApplication,fileStoreFactory,sessionSettings,fileLogFactory,
                    messageFactory);
            socketInitiator.start();
        } catch (ConfigError error) {
            error.printStackTrace();
        }

    }

    public static void sendFixMessage(Message message){
        SessionID sessionId = (SessionID) socketInitiator.getSessions().get(0);
        Session.lookupSession(sessionId);
        try{
            Session.sendToTarget(message,sessionId);
        }catch (SessionNotFound sessionNotFound){
            sessionNotFound.printStackTrace();
        }
    }
}
