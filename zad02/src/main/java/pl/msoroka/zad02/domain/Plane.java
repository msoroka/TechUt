package pl.msoroka.zad02.domain;

import java.sql.*;

public class Plane {
    private String producer;
    private int capacity;
    private Date produceDate;
    private double combustion;

    public Plane(String producer, int capacity, Date produceDate, double combustion) {
        this.producer = producer;
        this.capacity = capacity;
        this.produceDate = produceDate;
        this.combustion = combustion;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    public double getCombustion() {
        return combustion;
    }

    public void setCombustion(double combustion) {
        this.combustion = combustion;
    }
}
