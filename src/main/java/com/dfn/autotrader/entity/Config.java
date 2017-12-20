package com.dfn.autotrader.entity;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by darshanas on 11/26/2017.
 */
public class Config {

    public static List<Trader> getTraderList(){

        List<Trader> list = new ArrayList<>();

        Trader trader1 = new Trader();
        PortfolioEntry [] arrTrader1 = new PortfolioEntry[]{
                new PortfolioEntry("1010",50000)
        };
        Portfolio portfolioTr1 = new Portfolio();
        portfolioTr1.setAccountNo("793440170V");
        portfolioTr1.setPortfolioEntryList(Arrays.asList(arrTrader1));
        trader1.setCash(15000000);
        trader1.setTraderId("100");
        trader1.setPortfolio(portfolioTr1);
        trader1.setDelayInSec(2);
        list.add(trader1);

        Trader trader2 = new Trader();
        PortfolioEntry [] arrTrader2 = new PortfolioEntry[]{
                new PortfolioEntry("1010",50000)
        };
        Portfolio portfolioTr2 = new Portfolio();
        portfolioTr2.setAccountNo("751259482V");
        portfolioTr2.setPortfolioEntryList(Arrays.asList(arrTrader2));
        trader2.setCash(15000000);
        trader2.setTraderId("101");
        trader2.setPortfolio(portfolioTr2);
        trader2.setDelayInSec(1);
        list.add(trader2);

        Trader trader3 = new Trader();
        PortfolioEntry [] arrTrader3 = new PortfolioEntry[]{
                new PortfolioEntry("1010",50000)
        };

        Portfolio portfolioTr3 = new Portfolio();
        portfolioTr3.setAccountNo("825540852V");
        portfolioTr3.setPortfolioEntryList(Arrays.asList(arrTrader3));
        trader3.setCash(15000000);
        trader3.setTraderId("102");
        trader3.setPortfolio(portfolioTr3);
        trader3.setDelayInSec(1);
        list.add(trader3);
//
        Trader trader4 = new Trader();
        PortfolioEntry [] arrTrader4 = new PortfolioEntry[]{
                new PortfolioEntry("1010",50000)
        };
        Portfolio portfolioTr4 = new Portfolio();
        portfolioTr4.setAccountNo("852263125V");
        portfolioTr4.setPortfolioEntryList(Arrays.asList(arrTrader4));
        trader4.setCash(15000000);
        trader4.setTraderId("103");
        trader4.setPortfolio(portfolioTr4);
        trader4.setDelayInSec(1);
        list.add(trader4);

        return list;


    }

    public static List<Trader> getTraderList2(){

        List<Trader> list = new ArrayList<>();
        Random random = new Random();
        Trader trader = null;

        for(int i = 0; i < 4; i++){

            trader = new Trader();
            PortfolioEntry [] arrTrader3 = new PortfolioEntry[]{
                    new PortfolioEntry("1010",50000)
            };

            Portfolio portfolio = new Portfolio();
            portfolio.setAccountNo(Integer.toString(i));
            portfolio.setPortfolioEntryList(Arrays.asList(arrTrader3));
            trader.setPortfolio(portfolio);
            trader.setCash(150000);

            int x = random.nextInt(10);
            if(x == 0)
                x = 1;

            trader.setDelayInSec(3);
            trader.setTraderId(Integer.toString(i));
            list.add(trader);

        }

        return list;
    }

}
