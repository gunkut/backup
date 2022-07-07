package com.example.test.atm;

import com.example.test.Atm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class AtmBackend {

    public int twoHundredAmount;
    public int oneHundredAmount;
    public int fiftyAmount;
    public int twentyAmount;
    public int tenAmount;
    public int fiveAmount;
    private double balance;
    /*private double deposit;
    private double withdraw;*/
    SessionFactory factory;
    Session session;
    public AtmBackend() {

    }

    public void connectDatabase(){
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(com.example.test.Atm.class).buildSessionFactory();
        session = factory.getCurrentSession();
        session.beginTransaction();
    }
    public double getBalance() {
        int money = 0;
        try {
            connectDatabase();
            Atm atm = session.get(Atm.class, 1);
            money = (atm.getTwo_hundred()*200 + atm.getOne_hundred()*100 + atm.getFifty()*50 + atm.getTwenty()*20 + atm.getTen()*10 + atm.getFive()*5);
            this.printEachMoney(atm);
            session.getTransaction().commit();
            System.out.println("Done!!");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            factory.close();
        }
        return money;
    }
    public void printEachMoney(Atm atm){
        System.out.println("200 dollar:" + atm.getTwo_hundred());
        System.out.println("100 dollar:" + atm.getOne_hundred());
        System.out.println("50 dollar:" + atm.getFifty());
        System.out.println("20 dollar:" + atm.getTwenty());
        System.out.println("10 dollar:" + atm.getTen());
        System.out.println("5 dollar:" + atm.getFive());
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /*public int parse(int money, int currency, String op){
        Atm atm = session.get(Atm.class, 1);
        int amount = money / currency;
        if (op.equals("-")){
            if(amount != 0 && atm.getTwo_hundred() >= amount) {
                System.out.println("200 is working");
                twoHundredAmount = amount;
                money %= 200;
            }
            return money;
        } else {
            if(amount != 0) {
                twoHundredAmount = amount;
                money %= 200;
            }
        }

        return money;
    }*/
    public int parseTwoHundred(int money, String op, Atm atm){
        System.out.println("200 is checking");
        int amount = money / 200;
        if (op.equals("-")){
            if(amount != 0 && atm.getTwo_hundred() >= amount) {
                System.out.println("200 is working");
                twoHundredAmount = amount;
                money %= 200;

                return money;
            }
            return money;
        } else {
            if(amount != 0) {
                twoHundredAmount = amount;
                money %= 200;

                return money;
            }
            return money;
        }
    }

    public int parseOneHundred(int money, String op, Atm atm){
        System.out.println("100 is checking");
        System.out.println(atm.getOne_hundred());
        int amount = money / 100;
        if (op.equals("-")){
            if(amount != 0 && atm.getOne_hundred() >= amount) {
                System.out.println("100 is working");
                oneHundredAmount = amount;
                money %= 100;

                return money;
            }
            return money;
        } else {
            if(amount != 0) {
                oneHundredAmount = amount;
                money %= 100;

                return money;
            }
            return money;
        }
    }

    public int parseFifty(int money, String op, Atm atm){
        System.out.println("50 is checking");
        System.out.println(atm.getFifty());
        int amount = money / 50;
        if (op.equals("-")){
            if(amount != 0 && atm.getFifty() >= amount) {
                System.out.println("50 is working");
                fiftyAmount = amount;
                money %= 50;

                return money;
            }
            return money;
        } else {
            if(amount != 0) {
                fiftyAmount = amount;
                money %= 50;

                return money;
            }
            return money;
        }
    }

    public int parseTwenty(int money, String op, Atm atm){
        System.out.println("20 is checking");
        System.out.println(atm.getTwenty());
        int amount = money / 20;
        if (op.equals("-")){
            if(amount != 0 && atm.getTwenty() >= amount) {
                System.out.println("20 is working");
                twentyAmount = amount;
                money %= 20;

                return money;
            }
            return money;
        } else {
            if(amount != 0) {
                twentyAmount = amount;
                money %= 20;

                return money;
            }
            return money;
        }
    }

    public int parseTen(int money, String op, Atm atm){
        System.out.println("10 is checking");
        System.out.println(atm.getTen());
        int amount = money / 10;
        if (op.equals("-")){
            if(amount != 0 && atm.getTen() >= amount) {
                tenAmount = amount;
                money %= 10;

                return money;
            }
            return money;
        } else {
            if(amount != 0) {
                tenAmount = amount;
                money %= 10;

                return money;
            }
            return money;
        }
    }

    public int parseFive(int money, String op, Atm atm){
        System.out.println("5 is checking");
        System.out.println(atm.getFive());
        int amount = money / 5;
        if (op.equals("-")){
            if(amount != 0 && atm.getFive() >= amount) {
                fiveAmount = amount;
                money %= 5;

                return money;
            }
            return money;
        } else {
            if(amount != 0) {
                fiveAmount = amount;
                money %= 5;

                return money;
            }
            return money;
        }
    }

    public void updateBalance(int money, String op){

        System.out.println("selam");
        try {
            connectDatabase();
            Atm atm = session.get(Atm.class, 1);
            if (op.equals("-")) {
                System.out.println("op is minus");
                if (money <= getBalance()) {
                    System.out.println("requested money is available");
                    money = parseTwoHundred(money, "-", atm);
                    if (atm.getTwo_hundred() >= twoHundredAmount) {
                        System.out.println(twoHundredAmount);
                        atm.setTwo_hundred(atm.getTwo_hundred() - twoHundredAmount );
                    }
                    money = parseOneHundred(money, "-", atm);
                    if (atm.getOne_hundred() >= oneHundredAmount) {
                        System.out.println("adet:" + oneHundredAmount);
                        atm.setOne_hundred(atm.getOne_hundred() - oneHundredAmount );
                    }
                    money = parseFifty(money, "-", atm);
                    if (atm.getFifty() >= fiftyAmount) {
                        System.out.println("adet:" + fiftyAmount);
                        atm.setFifty(atm.getFifty() - fiftyAmount );
                    }
                    money = parseTwenty(money, "-", atm);
                    if (atm.getTwenty() >= twentyAmount) {
                        System.out.println("adet:" + twentyAmount);
                        atm.setTwenty(atm.getTwenty() - twentyAmount );
                    }
                    money = parseTen(money, "-", atm);
                    if (atm.getTen() >= tenAmount) {
                        System.out.println("adet:" + tenAmount);
                        atm.setTen(atm.getTen() - tenAmount );
                    }
                    parseFive(money ,"-", atm);
                    if (atm.getFive() >= fiveAmount) {
                        System.out.println("adet:" + fiveAmount);
                        atm.setFive(atm.getFive() - fiveAmount );
                    }
                } else {
                    System.out.println("Insufficient Balance");
                }
            } else {
                System.out.println("op is positive");
                money = parseTwoHundred(money, "+", atm);
                atm.setTwo_hundred(atm.getTwo_hundred() + twoHundredAmount);

                money = parseOneHundred(money, "+", atm);
                atm.setOne_hundred(atm.getOne_hundred() + oneHundredAmount);

                money = parseFifty(money, "+", atm);
                atm.setFifty(atm.getFifty() + fiftyAmount);

                money = parseTwenty(money, "+", atm);
                atm.setTwenty(atm.getTwenty() + twentyAmount);

                money = parseTen(money, "+", atm);
                atm.setTen(atm.getTen() + tenAmount);

                parseFive(money, "+", atm);
                atm.setFive(atm.getFive() + fiveAmount);
            }
            session.getTransaction().commit();
            System.out.println("Done!!");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            factory.close();
        }
    }
}
