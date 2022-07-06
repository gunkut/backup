package com.example.test.atm;

import com.example.test.Atm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class AtmBackend {
    private double balance;
    /*private double deposit;
    private double withdraw;*/
    SessionFactory factory;
    Session session;
    public AtmBackend() {

    }

    /*public AtmBackend(double balance, double deposit, double withdraw) {
        this.balance = balance;
        this.deposit = deposit;
        this.withdraw = withdraw;
    }*/
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

    /*public int checkdatabase(){};*/
    public int parseMoney(double amount, double number){
        return amount / number
    };
    public void updateBalance(double money, char c){

        try {
            connectDatabase();
            Atm atm = session.get(Atm.class, 1);
            if (c == '-') {
                if (money <= this.getBalance()) {
                    while (money > 0) {


                    }
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
