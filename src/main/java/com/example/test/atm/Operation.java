package com.example.test.atm;

public interface Operation {
    public void viewMoney();
    public void withdrawAmount(double withdrawAmount);
    public void depositAmount(double depositAmount);
    public void currencyAndAmounts(double money, String sign);
}
