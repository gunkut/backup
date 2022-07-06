package com.example.test.atm;

import com.example.test.Atm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class AtmBackend {

    public static int twoHundredAmount;
    public static int oneHundredAmount;
    public static int fiftyAmount;
    public static int twentyAmount;
    public static int tenAmount;
    public static int fiveAmount;
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
        double money = 0;
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

    public int checkTwoHundred(int money){
        Atm atm = session.get(Atm.class, 1);
        if(atm.getTwo_hundred() != 0  && atm.getTwo_hundred() >= money / 200) {
            twoHundredAmount = money / 200;
            money %= 200;

            return money;
        } else {
            return money;
        }
    }

    public int checkOneHundred(int money){
        Atm atm = session.get(Atm.class, 1);
        if(atm.getOne_hundred() != 0  && atm.getOne_hundred() >= money / 200) {
            oneHundredAmount = money / 100;
            money %= 100;

            return money;
        } else {
            return money;
        }
    }

    public int checkFifty(int money){
        Atm atm = session.get(Atm.class, 1);
        if(atm.getFifty() != 0  && atm.getFifty() >= money / 50) {
            fiftyAmount = money / 50;
            money %= 50;

            return money;
        } else {
            return money;
        }
    }

    public int checkTwenty(int money){
        Atm atm = session.get(Atm.class, 1);
        if(atm.getTwenty() != 0  && atm.getTwenty() >= money / 20) {
            twentyAmount = money / 20;
            money %= 20;

            return money;
        } else {
            return money;
        }
    }

    public int checkTen(int money){
        Atm atm = session.get(Atm.class, 1);
        if(atm.getTen() != 0  && atm.getTen() >= money / 10) {
            tenAmount = money / 10;
            money %= 10;

            return money;
        } else {
            return money;
        }
    }

    public int checkFive(int money){
        Atm atm = session.get(Atm.class, 1);
        if(atm.getFive() != 0  && atm.getFive() >= money / 5) {
            fiveAmount = money / 5;
            money %= 5;

            return money;
        } else {
            return money;
        }
    }

    public void updateBalance(int money, String op){

        System.out.println("selam");
        try {
            connectDatabase();
            Atm atm = session.get(Atm.class, 1);
            if (op.equals("-")) {
                System.out.println("c is minus");
                if (money <= getBalance()) {

                    checkTwoHundred(money);
                    checkOneHundred(checkTwoHundred(money));
                    checkFifty(checkOneHundred(money));
                    checkTwenty(checkFifty(money));
                    checkTen(checkTwenty(money));
                    checkFive(checkTen(money));

                    atm.setTwo_hundred(atm.getTwo_hundred() - twoHundredAmount );
                    atm.setOne_hundred(atm.getTwo_hundred() - oneHundredAmount );
                    atm.setFifty(atm.getTwo_hundred() - fiftyAmount );
                    atm.setTwenty(atm.getTwo_hundred() - twentyAmount );
                    atm.setTen(atm.getTwo_hundred() - tenAmount );
                    atm.setFive(atm.getTwo_hundred() - fiveAmount );
                } else {
                    System.out.println("Insufficient Balance");
                }
            } else {
                if  (money == 5) {
                    atm.setFive(atm.getFive() + 1);
                } else if (money == 10) {
                    atm.setTen(atm.getTen() + 1);
                } else if (money == 20) {
                    atm.setTwenty(atm.getTwenty() + 1);
                } else if (money == 50) {
                    atm.setFifty(atm.getFifty() + 1);
                } else if (money == 100) {
                    atm.setOne_hundred(atm.getOne_hundred() + 1);
                } else if (money == 200) {
                    atm.setTwo_hundred(atm.getTwo_hundred() + 1);
                }
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
