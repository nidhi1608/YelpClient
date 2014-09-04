package com.example.yelpclient.app.models;

public class Location {
    private String[] address;
    private String[] neighborhoods;

    public String getAddress() {
        return address[0];
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhoods[0];
    }

    public void setNeighborhoods(String[] neighborhoods) {
        this.neighborhoods = neighborhoods;
    }
}
