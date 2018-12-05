package pl.msoroka.zad05.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plane {

    private long id;
    private String name;
    private String produceDate;
    private int yop;

    public Plane(String name, String produceDate, int yop) {
        this.name = name;
        this.produceDate = produceDate;
        this.yop = yop;
    }

    public Plane() {
    }

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

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public int getYop() {
        return yop;
    }

    public void setYop(int yop) {
        this.yop = yop;
    }
}
