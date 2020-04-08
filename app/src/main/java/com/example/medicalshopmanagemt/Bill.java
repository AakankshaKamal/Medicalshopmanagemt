package com.example.medicalshopmanagemt;

public class Bill {


    private String qr,time;
    public Bill()
    {

    }
    public Bill(String qr, String time) {
        this.qr = qr;
        this.time = time;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
