package com.dfn.autotrader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by darshanas on 12/6/2017.
 */
public class FixAnalyzer {

    private static int totalFixMessages;
    private static int newOrders;
    private static int adminMessages;
    private static int executions;
    private static int rejections;
    private static int partiolFills;
    private static int fills;
    private static int ordResponses;
    private static final char delimiter = '\u0001';

    public static void main(String[] args) {

        String fileName = "F:\\iolos\\AlgoTrading\\AutoTrader\\Client_Logs\\FIX.4.2-AUTO-TRADER-ALGO-EXCHANGE.messages.log";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))){
            stream.forEach(fix -> {
                totalFixMessages++;
                String [] arr = fix.split(String.valueOf(delimiter));
                boolean flag = false;

                for(String str : arr){

                    try {

                        if(str != null && !str.equals("")) {


                            int key = Integer.parseInt(str.substring(0, str.indexOf("=")));
                            String val = str.substring(str.indexOf("=") + 1);

                            if (key == 35) {
                                if (val.equals("D")) {
                                    //flag = false;
                                    newOrders++;
                                } else if (val.equals("8")) {
                                    executions++;
                                    flag = true;
                                } else if (val.equals("A")) {
                                    //flag = false;
                                    adminMessages++;
                                }
                            }else if(key == 39){

                                if(flag){
                                    if(val.equals("0")){
                                        ordResponses++;
                                    }else if(val.equals("1")){
                                        partiolFills++;
                                    }else if(val.equals("2")){
                                        fills++;
                                    }else if(val.equals("8")){
                                        rejections++;
                                    }
                                }

                            }

                        }

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    } catch (StringIndexOutOfBoundsException e){
                        System.out.println("ERR IN MSG " + fix);
                        e.printStackTrace();
                    }
                }
            });

            System.out.println("TOTAL FIX MESSAGES : " + totalFixMessages);
            System.out.println("TOTAL NEW ORDERS   : " + newOrders);
            System.out.println("TOTAL EXECUTIONS   : " + executions);
            System.out.println("TOTAL ADMIN MSGS   : " + adminMessages);
            System.out.println("QUEUES " + ordResponses + " P.FILS " + partiolFills + " FILLS " + fills +
                    " REJECTIONS " + rejections ) ;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
