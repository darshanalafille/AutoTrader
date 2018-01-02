package com.dfn.autotrader.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.dfn.autotrader.entity.Symbol;
import com.dfn.autotrader.entity.price.PriceEvent;
import com.dfn.autotrader.entity.price.SymbolList;
import com.dfn.autotrader.store.PriceStore;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by darshanas on 11/26/2017.
 */
public class FeedHandler extends UntypedActor {

    private Gson gson = new Gson();
    private JsonParser parser = new JsonParser();

    @Override
    public void preStart() throws Exception {
        super.preStart();
        FeedSocket feedSocket = new FeedSocket();
        new Thread(() -> feedSocket.startRead()).start();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof String){
            processMessage((String)message);
        }
    }


    private void processMessage(String message){
        //System.out.println(message);
        JsonObject object = parser.parse(message).getAsJsonObject();
        String messageType = object.get("messageType").getAsString();
        if(messageType.equals("S")){
            SymbolList list = gson.fromJson(message,SymbolList.class);
            list.getSymbolList().forEach(s -> {
                SymbolStore.put(new Symbol(s.getSymbolCode()));
            });
        }else if(messageType.equals("V") || messageType.equals("8")){

            String symbol = object.get("symbol").getAsString();
            PriceEvent event = PriceStore.getLastPriceEvent(symbol);
            if(event == null)
                event = new PriceEvent();

            if(messageType.equals("V")){
                double askVol = object.get("sellSide").getAsDouble();
                event.setAskVol(askVol);
                double bidVol = object.get("buySide").getAsDouble();
                event.setBidVol(bidVol);
                double ltp = object.get("lastTradePrice").getAsDouble();
                event.setLastTradePrice(ltp);
            }else if(messageType.equals("8")){
                double qty = object.get("qty").getAsDouble();
                event.setLastTradeQty(qty);
                double price = object.get("price").getAsDouble();
                event.setLastTradePrice(price);
            }

            PriceStore.addPriceEvent(symbol,event);

        }
    }


    class FeedSocket {

        private Socket socket;
        BufferedReader input;
        String priceMessage;

        public FeedSocket(){
            while (!connect()){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean connect() {
            try {
                System.out.println("Connecting with price socket");
                socket = new Socket("192.168.16.91",16500);
//                socket = new Socket("127.0.0.1",16500);
                return true;
            } catch (IOException e) {
                System.out.println("Cant connect to specified host");
                return false;
            }

        }

        public void startRead(){

            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while ((priceMessage = input.readLine()) != null){
                    getSelf().tell(priceMessage,getSelf());
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }

}
