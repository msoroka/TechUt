package pl.msoroka.techut.plane.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "plane.all", query = "Select p from Plane p"),
})
public class Plane {

    private long id;
    private String model;
    private String sideNumber;
    private int capacity;
    private Date produceDate;

    private List<Pilot> pilots = new ArrayList<Pilot>();

    public Plane(String model, String sideNumber, int capacity, Date produceDate) {
        this.model = model;
        this.sideNumber = sideNumber;
        this.capacity = capacity;
        this.produceDate = produceDate;
    }

    public Plane() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(unique = true, nullable = false)
    public String getSideNumber() {
        return sideNumber;
    }

    public void setSideNumber(String sideNumber) {
        this.sideNumber = sideNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Temporal(TemporalType.DATE)
    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }
}
