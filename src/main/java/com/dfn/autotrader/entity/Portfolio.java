package com.dfn.autotrader.entity;

import java.util.List;

/**
 * Created by darshanas on 11/26/2017.
 */
public class Portfolio {

    private String accountNo;
    private List<PortfolioEntry> portfolioEntryList;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public List<PortfolioEntry> getPortfolioEntryList() {
        return portfolioEntryList;
    }

    public void setPortfolioEntryList(List<PortfolioEntry> portfolioEntryList) {
        this.portfolioEntryList = portfolioEntryList;
    }
}
