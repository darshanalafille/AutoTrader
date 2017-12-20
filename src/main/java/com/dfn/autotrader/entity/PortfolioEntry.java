package com.dfn.autotrader.entity;

/**
 * Created by darshanas on 11/26/2017.
 */
public class PortfolioEntry {

    private String symbolName;
    private long qty;

    public PortfolioEntry(String symbolName, long qty) {
        this.symbolName = symbolName;
        this.qty = qty;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }
}
