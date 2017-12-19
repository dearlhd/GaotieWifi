package com.dearlhd.crhwifi.SDK.bean;

/**
 * Created by dearlhd on 2017/12/18.
 */
public class Journey {
    private String destination;
    private String currentStation;
    private String arrivalTime;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(String currentStation) {
        this.currentStation = currentStation;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
