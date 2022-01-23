package com.mycompany.dailyexpenses.models;

import java.util.Date;

public class Expense {

    private String UsernameE;
    private String Category;
    private double Amount;
    private Date Date;

    public Expense(String UsernameE, String Category, double Amount, Date Date) {
        this.UsernameE = UsernameE;
        this.Category = Category;
        this.Amount = Amount;
        this.Date = Date;
    }

    public String getUsernameE() {
        return UsernameE;
    }

    public void setUsernameE(String UsernameE) {
        this.UsernameE = UsernameE;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

  
}
