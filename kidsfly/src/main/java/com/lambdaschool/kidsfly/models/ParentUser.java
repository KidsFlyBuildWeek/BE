package com.lambdaschool.kidsfly.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "parentusers")
public class ParentUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long parentid;
    @Column(unique = true,
            nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phone;
    @Column(unique = true,
            nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String airport;
    @Column(nullable = false)
    private String status;


    @OneToMany(mappedBy = "parentuser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("parentuser")
    private List<Trip> trips = new ArrayList<>();

    // constructors


    public ParentUser(String username, String password, String phone, String email, String name, String address, String airport) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.address = address;
        this.airport = airport;
    }

    public ParentUser() {
    }


    // getters and setters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getParentid() {
        return parentid;
    }

    public void setParentid(long parentid) {
        this.parentid = parentid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
