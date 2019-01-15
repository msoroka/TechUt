package pl.msoroka.techut.plane.domain;

import javax.persistence.*;


@Entity
public class License {

    private long id;
    private String licenseNumber;

    public License(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public License() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
