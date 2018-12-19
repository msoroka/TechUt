package pl.msoroka.techut.plane.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "producer.all", query = "Select p from Producer p"),
})
public class Producer {

    private long id;
    private String model;

    public Producer(String model) {
        this.model = model;
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
}
