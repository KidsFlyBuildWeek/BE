package com.lambdaschool.kidsfly.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "staffusers")
public class StaffUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long staffid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String status;
    @OneToMany(mappedBy = "staffuser", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnoreProperties("staffuser")
    private List<Trip> trips = new ArrayList<>();

    public StaffUser(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public StaffUser() {
    }

    public long getStaffid() {
        return staffid;
    }

    public void setStaffid(long staffid) {
        this.staffid = staffid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
