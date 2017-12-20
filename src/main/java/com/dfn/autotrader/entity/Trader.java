package com.dfn.autotrader.entity;

/**
 * Created by darshanas on 11/26/2017.
 */
public class Trader {

    private String traderId;
    private Portfolio portfolio;
    private double cash;
    private int delayInSec;

    public String getTraderId() {
        return traderId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public int getDelayInSec() {
        return delayInSec;
    }

    public void setDelayInSec(int delayInSec) {
        this.delayInSec = delayInSec;
    }
}
