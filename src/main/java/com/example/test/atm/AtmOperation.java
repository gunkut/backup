package com.example.test.atm;

import java.util.HashMap;

public class AtmOperation implements Operation {
    AtmBackend atm = new AtmBackend();
    HashMap<Double, Double> statement = new HashMap<>();

    @Override
    public void viewMoney() {
        System.out.println("You have " + atm.getBalance() + "$");
    }

    @Override
    public void withdrawAmount(double withdrawAmount) {
        atm.updateBalance(withdrawAmount, '-');

        /*if(withdrawAmount <= atm.getBalance()) {
            for (double i : statement.keySet()) {
                if (withdrawAmount != i) {
                    System.out.println("No banknote");
                } else {
                    System.out.println("Here is your " + withdrawAmount + "$");
                    atm.setBalance(atm.getBalance() - withdrawAmount);
                    viewMoney();
                }
            }

        } else {
            System.out.println("Insufficient Balance");
    }
        currencyAndAmounts(withdrawAmount, "-");*/
}

    @Override
    public void currencyAndAmounts(double money, String sign) {

        if (sign.equals("+")) {
            if(statement.containsKey(money)) {
                statement.replace(money, statement.get(money) + 1.0);
            } else {
                statement.put(money, 1.0);
            }
        } else {
            if(atm.getBalance() == 0.0) {
                statement.clear();
            } else {
                if(statement.containsKey(money)) {
                    statement.replace(money, statement.get(money) - 1);
                    if (statement.get(money) == 0.0) {
                        statement.remove(money);
                    }
                }
            }
        }
        for (double i : statement.keySet()) {
            System.out.println("Currency: " + i + " Amount: " + statement.get(i));
        }
    }

    @Override
    public void depositAmount(double depositAmount) {
        atm.updateBalance(depositAmount, '+');

        /*atm.setBalance(atm.getBalance() + depositAmount);*/
        viewMoney();
        System.out.println(depositAmount + "$ deposited successfully");

        /*currencyAndAmounts(depositAmount, "+");*/
    }
}























