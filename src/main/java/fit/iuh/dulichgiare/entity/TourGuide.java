package fit.iuh.dulichgiare.entity;
// Generated Feb 8, 2023, 10:55:58 AM by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Tourguide generated by hbm2java
 */
@Entity
@Table(name = "tourguide")
public class TourGuide implements java.io.Serializable {

    private long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Set<Tour> tours;

    public TourGuide() {
    }

    public TourGuide(long id, String name, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public TourGuide(long id, String name, String address, String phone, String email, Set<Tour> tours) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.tours = tours;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "phone", nullable = false, length = 10)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tourguide")
    public Set<Tour> getTours() {
        return this.tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

}