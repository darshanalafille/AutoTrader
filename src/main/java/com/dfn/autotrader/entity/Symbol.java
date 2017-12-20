package com.dfn.autotrader.entity;

/**
 * Created by darshanas on 11/26/2017.
 */
public class Symbol {

    private String name;
    private long volume;
    private double price;

    public Symbol(){

    }

    public Symbol(String name, long volume, double price) {
        this.name = name;
        this.volume = volume;
        this.price = price;
    }

    public Symbol(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
