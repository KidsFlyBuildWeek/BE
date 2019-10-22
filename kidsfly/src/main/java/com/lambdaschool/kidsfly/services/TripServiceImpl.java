package com.lambdaschool.kidsfly.services;

import com.lambdaschool.kidsfly.models.Trip;
import com.lambdaschool.kidsfly.repos.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "staffService")
public class TripServiceImpl implements TripService {
    @Autowired
    private TripRepository triprepos;

    @Override
    public List<Trip> findAll() {
        List<Trip> rtnList = new ArrayList<>();
        triprepos.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
        public Trip findTripById(long id) {
            return triprepos.findById(id).orElseThrow(()->new EntityNotFoundException("Id" + id));
        }
    // String date, int passengercount, int childcount, String airport, String luggagetype, ParentUser parentuser
    @Override
    public Trip save(Trip trip) {
        Trip newtrip = new Trip();

        newtrip.setDate(trip.getDate());
        newtrip.setPassengercount(trip.getPassengercount());
        newtrip.setChildcount(trip.getChildcount());
        newtrip.setAirport(trip.getAirport());
        newtrip.setLuggagetype(trip.getLuggagetype());
        newtrip.setParentuser(trip.getParentuser());
        newtrip.setStaffUser(null);

        return triprepos.save(newtrip);
    }
    // String date, int passengercount, int childcount, String airport, String luggagetype, ParentUser parentuser, StaffUser staffuser
    @Override
    public Trip update(Trip trip, long id) {
        Trip currenttrip = triprepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if(trip.getDate() != null){
            currenttrip.setDate(trip.getDate());
        }

        if(trip.getPassengercount() != 0){
            currenttrip.setPassengercount(trip.getPassengercount());
        }

        if(trip.getChildcount() != 0){
            currenttrip.setChildcount(trip.getChildcount());
        }

        if(trip.getAirport() != null){
            currenttrip.setAirport(trip.getAirport());
        }

        if(trip.getLuggagetype() != null){
            currenttrip.setLuggagetype(trip.getLuggagetype());
        }

        if(trip.getParentuser() != null){
            currenttrip.setParentuser(trip.getParentuser());
        }

        if(trip.getStaffUser() != null){
            currenttrip.setStaffUser(trip.getStaffUser());
        }

        return triprepos.save(currenttrip);
    }
}
