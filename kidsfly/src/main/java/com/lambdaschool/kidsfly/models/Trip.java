package com.lambdaschool.kidsfly.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tripid;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private int passengercount;
    @Column(nullable = false)
    private int childcount;
    @Column(nullable = false)
    private String airport;
    @Column(nullable = false)
    private String luggagetype;

    @ManyToOne
    @JoinColumn(name = "parentid", nullable = false)
    @JsonIgnoreProperties("trips")
    private ParentUser parentuser;

    @ManyToOne
    @JoinColumn(name = "staffid", nullable = true)
    @JsonIgnoreProperties("trips")
    private StaffUser staffuser;

    public Trip(String date, int passengercount, int childcount, String airport, String luggagetype, ParentUser parentuser) {
        this.date = date;
        this.passengercount = passengercount;
        this.childcount = childcount;
        this.airport = airport;
        this.luggagetype = luggagetype;
        this.parentuser = parentuser;
        this.staffuser = null;
    }

    public Trip() {
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public int getChildcount() {
        return childcount;
    }

    public void setChildcount(int childcount) {
        this.childcount = childcount;
    }

    public long getTripid() {
        return tripid;
    }

    public void setTripid(long tripid) {
        this.tripid = tripid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPassengercount() {
        return passengercount;
    }

    public void setPassengercount(int passengercount) {
        this.passengercount = passengercount;
    }

    public String getLuggagetype() {
        return luggagetype;
    }

    public void setLuggagetype(String luggagetype) {
        this.luggagetype = luggagetype;
    }

    public ParentUser getParentuser() {
        return parentuser;
    }

    public void setParentuser(ParentUser parentuser) {
        this.parentuser = parentuser;
    }

    public StaffUser getStaffUser() {
        return staffuser;
    }

    public void setStaffUser(StaffUser staffuser) {
        this.staffuser = staffuser;
    }
}
