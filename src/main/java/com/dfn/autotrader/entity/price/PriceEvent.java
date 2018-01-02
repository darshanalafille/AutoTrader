package com.dfn.autotrader.entity.price;

/**
 * Created by darshanas on 12/21/2017.
 */
public class PriceEvent {

    private String symbol;
    private double bidVol;
    private double askVol;
    private double lastTradeQty;
    private double lastTradePrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getBidVol() {
        return bidVol;
    }

    public void setBidVol(double bidVol) {
        this.bidVol = bidVol;
    }

    public double getAskVol() {
        return askVol;
    }

    public void setAskVol(double askVol) {
        this.askVol = askVol;
    }

    public double getLastTradeQty() {
        return lastTradeQty;
    }

    public void setLastTradeQty(double lastTradeQty) {
        this.lastTradeQty = lastTradeQty;
    }

    public double getLastTradePrice() {
        return lastTradePrice;
    }

    public void setLastTradePrice(double lastTradePrice) {
        this.lastTradePrice = lastTradePrice;
    }
}
