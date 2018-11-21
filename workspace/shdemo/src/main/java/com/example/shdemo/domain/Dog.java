package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
public class Dog {

    private long id;
    private String name;
    private int yob;


    private Person owner;

    public Dog(String name, int yob) {
        this.name = name;
        this.yob = yob;
    }

    public Dog(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

    @ManyToOne
    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
