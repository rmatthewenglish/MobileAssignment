package com.example.locationservice;

public class Addresses {

    String _address;
    String _latitude;
    String _longitude;
    public Addresses(){   }
    public Addresses(String address, String latitude, String longitude){
        this._address = address;
        this._latitude = latitude;
        this._longitude = longitude;
    }

    public String getAddress(){
        return this._address;
    }

    public void setAddress(String address){
        this._address = address;
    }

    public String getLatitude(){
        return this._latitude;
    }

    public void setLatitude(String latitude){
        this._latitude = latitude;
    }

    public String getLongitude(){
        return this._longitude;
    }

    public void setLongitude(String longitude){
        this._longitude = longitude;
    }
}