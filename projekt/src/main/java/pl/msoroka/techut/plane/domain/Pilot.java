package pl.msoroka.techut.plane.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "pilot.byLicenseNumber", query = "SELECT p FROM Pilot p JOIN p.license l WHERE l.licenseNumber = :licenseNumber")
})
public class Pilot {

    private long id;
    private String firstName;
    private String lastName;
    private Date yob;

    private License license;

    public Pilot(String firstName, String lastName, Date yob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yob = yob;
    }

    public Pilot() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    public Date getYob() {
        return yob;
    }

    public void setYob(Date yob) {
        this.yob = yob;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }
}
