package com.example.myfirstapp;

public class Hotel {

    private String n;
    private String rate;
    private String address;
    private String image;


    public Hotel(String n, String rate, String address,String imag) {
        this.n = n;
        this.rate = rate;
        this.address = address;
        this.image=imag;
    }

    public String getN() {
        return n;
    }

    public String getRate() {
        return rate;
    }

    public String getAddress() {
        return address;
    }
    public String getImage()
    {
        return image;
    }

    public void setN(String n) {
        this.n = n;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImage(String image) {
        this.image = image;
    }
}