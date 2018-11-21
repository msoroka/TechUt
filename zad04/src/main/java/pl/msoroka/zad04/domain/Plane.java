package pl.msoroka.zad04.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plane {

    private long id;
    private String name;
    private String produceDate;
    private int yop;

    private Producer producer;
    private License license;
    private List<Pilot> pilots = new ArrayList<Pilot>();

    public Plane(String name, String produceDate, int yop, Producer producer, License license, List<Pilot> pilots) {
        this.name = name;
        this.produceDate = produceDate;
        this.yop = yop;
        this.producer = producer;
        this.license = license;
        this.pilots = pilots;
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

    @ManyToOne
    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @OneToOne
    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    @ManyToMany
    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }
}
