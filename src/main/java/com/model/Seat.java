package com.model;

public class Seat {
    private int id;
    private int row;
    private int number;
    private double price;
    private int accId;

    public Seat() {
    }

    public static Seat of(int id, int row, int number, double price, int accId) {
        Seat rsl = new Seat();
        rsl.setId(id);
        rsl.setRow(row);
        rsl.setNumber(number);
        rsl.setPrice(price);
        rsl.setAccId(accId);
        return rsl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }
}
