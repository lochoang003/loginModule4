package com.example.login_logout.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double total;
    private Date date;
    @ManyToOne
    private Account account;

    public Bill() {
    }

    public Bill(int id, double total, Date date, Account account) {
        this.id = id;
        this.total = total;
        this.date = date;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
