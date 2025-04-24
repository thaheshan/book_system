package com.thaheshan.bookstoreapplication.Models;

import java.util.Map;

public class Order {
    private int orderID;
    private int ID;
    private int customerID;
    private int bookID;
    private Map<String, Integer> items;
    private Double total_amount;

    public Order() {

    }

    public Order(int orderID, int customerID, int bookID, Map<String, Integer> items, Double total_amount) {
        this.orderID = orderID;
        this.ID = ID;
        this.customerID = customerID;
        this.bookID = bookID;
        this.items = items;
        this.total_amount = total_amount;

    }

    public int getOrderID() {
        return orderID;

    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;

    }


    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }


    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }


    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }


    public Map<String, Integer> getItems() {
        return items;
    }
    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }


    public Double getTotal_amount() {
        return total_amount;
    }
    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }
}

