package com.randomplayer.infibookmm_app.models;


import java.util.Date;

public class Transaction {
    private User lender, borrower;
    private Date time;
    private String book;
    public Transaction() {
    }

    public Transaction(User lender, User borrower, Date time, String book) {
        this.lender = lender;
        this.borrower = borrower;
        this.time = time;
        this.book = book;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public User getLender() {
        return lender;
    }

    public void setLender(User lender) {
        this.lender = lender;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
