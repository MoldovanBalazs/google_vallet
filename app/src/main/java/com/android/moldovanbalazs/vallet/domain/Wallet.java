package com.android.moldovanbalazs.vallet.domain;

import java.util.Date;

public class Wallet {

    private Double sum;
    private Date registrationDate;
    private Date expirationDate;
    private Currency currency;

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Wallet(Double sum, Date registrationDate, Date expirationDate, Currency currency) {
        this.sum = sum;
        this.registrationDate = registrationDate;
        this.expirationDate = expirationDate;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "sum=" + sum +
                ", registrationDate=" + registrationDate +
                ", expirationDate=" + expirationDate +
                ", currency=" + currency +
                '}';
    }

    public enum Currency{
        EURO, LEI, DOLLAR;
    }



}
